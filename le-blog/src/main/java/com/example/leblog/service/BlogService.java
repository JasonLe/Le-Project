package com.example.leblog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.leblog.dto.request.GetDetailReqDTO;
import com.example.leblog.dto.request.ListBlogReqDTO;
import com.example.leblog.dto.request.SaveBlogReqDTO;
import com.example.leblog.entity.BlogEntity;
import com.example.leblog.mapper.BlogMapper;
import com.project.lecommon.result.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    public PageResult<List<BlogEntity>> getBlogs(ListBlogReqDTO reqDTO) {
//        Integer pageNum = PageUtil.getPageNum(reqDTO.getPageNum(), reqDTO.getPageSize());
//        Long count = blogMapper.selectCount(null);
//        List<BlogEntity> blogEntities = blogMapper.selectList(new LambdaQueryWrapper<BlogEntity>().last(String.format("limit %d,%d", pageNum, reqDTO.getPageSize())));
//        return Page.<List<BlogEntity>>builder().pageNum(pageNum).pageSize(reqDTO.getPageSize()).pageTotal(count.intValue()).data(blogEntities).build();

        Page<BlogEntity> blogEntityPage = blogMapper.selectPage(new Page<>(reqDTO.getPageNum(), reqDTO.getPageSize()), null);

        return PageResult.<List<BlogEntity>>builder()
                .pageNum(blogEntityPage.getCurrent())
                .pages(blogEntityPage.getPages())
                .pageTotal(blogEntityPage.getTotal())
                .data(blogEntityPage.getRecords())
                .build();
    }

    public void saveBlog(SaveBlogReqDTO saveBlogReqDTO) {
        // todo 参数校验
        BlogEntity blogEntity = BlogEntity.builder()
                .title(saveBlogReqDTO.getTitle())
                .digest(getDigest(saveBlogReqDTO.getContent()))
                .content(saveBlogReqDTO.getContent())
                .createTime(new Date())
                .updateTime(new Date())
                .status(0)
                .build();
        blogMapper.insert(blogEntity);
    }

    String getDigest(String content) {
        // todo 摘要提取
        return content.substring(0,10);
    }

    public BlogEntity getBlogDetail(GetDetailReqDTO reqDTO) {
        return blogMapper.selectById(Integer.parseInt(reqDTO.getBlogId()));
    }
}
