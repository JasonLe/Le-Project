package com.example.leblog.service;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.leblog.dto.request.GetDetailReqDTO;
import com.example.leblog.dto.request.ListBlogReqDTO;
import com.example.leblog.dto.request.SaveBlogReqDTO;
import com.example.leblog.dto.response.ListBlogResDTO;
import com.example.leblog.entity.BlogEntity;
import com.example.leblog.mapper.BlogMapper;
import com.example.leblog.utils.MarkdownUtils;
import com.example.leblog.utils.RedisUtils;
import com.project.lecommon.result.PageResult;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author whl
 * @Description:
 * @date 2023/8/2
 */
@Service
public class BlogService {

    @Resource
    private BlogMapper blogMapper;

    @Resource
    private RedisUtils redisUtils;

    public PageResult<List<BlogEntity>> getBlogs(ListBlogReqDTO reqDTO) {
        Page<BlogEntity> blogEntityPage = blogMapper.selectPage(new Page<>(reqDTO.getPageNum(), reqDTO.getPageSize()), null);

        return PageResult.<List<BlogEntity>>builder()
                .pageNum(blogEntityPage.getCurrent())
                .pages(blogEntityPage.getPages())
                .pageTotal(blogEntityPage.getTotal())
                .data(blogEntityPage.getRecords())
                .build();
    }

    public List<ListBlogResDTO> getByType(ListBlogReqDTO reqDTO) {

        String redisKey = "searchTypeKey:" + reqDTO.getType();
        try {
            Object o = redisUtils.get(redisKey);
            if (ObjectUtil.isNotNull(o)) {
                return (List<ListBlogResDTO>) o;
            }
        } catch (Exception e) {

        }

        Integer type = reqDTO.getType();
        List<BlogEntity> entityList = blogMapper.selectList(new QueryWrapper<BlogEntity>()
                .eq("type", type));

        List<ListBlogResDTO> list = new ArrayList<>(entityList.size());


        for (BlogEntity blogEntity : entityList) {
            ListBlogResDTO temp = new ListBlogResDTO();
            BeanUtils.copyProperties(blogEntity, temp);
            if (type == 0) {
                temp.setContent("");
                temp.setDigest("");
            }
            list.add(temp);
        }

        redisUtils.set(redisKey, list, 60);
        return list;
    }

    public void saveBlog(SaveBlogReqDTO saveBlogReqDTO) {
        BlogEntity blogEntity = BlogEntity.builder()
                .title(saveBlogReqDTO.getTitle())
                .digest(getDigest(saveBlogReqDTO.getContent()))
                .content(saveBlogReqDTO.getContent())
                .type(0)
                .status(0)
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        blogMapper.insert(blogEntity);
    }

    /**
     * 简易的获取摘要
     */
    String getDigest(String content) {
        return content.substring(0, 10) + "...";
    }

    public BlogEntity getBlogDetail(GetDetailReqDTO reqDTO) {
        BlogEntity blogEntity = blogMapper.selectById(Integer.parseInt(reqDTO.getBlogId()));
        blogEntity.setContent(MarkdownUtils.markdownToHtmlExtensions(blogEntity.getContent()));
        return blogEntity;
    }
}
