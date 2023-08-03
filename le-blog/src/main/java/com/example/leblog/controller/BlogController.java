package com.example.leblog.controller;

import com.example.leblog.dto.request.GetDetailReqDTO;
import com.example.leblog.dto.request.ListBlogReqDTO;
import com.example.leblog.dto.request.SaveBlogReqDTO;
import com.example.leblog.entity.BlogEntity;
import com.example.leblog.service.BlogService;
import com.project.lecommon.annotation.AuthType;
import com.project.lecommon.annotation.NeedAuth;
import com.project.lecommon.result.PageResult;
import com.project.lecommon.result.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author whl
 * @Description:
 * @date 2023/7/31
 */
@RequestMapping("/le-blog/blogs")
@NeedAuth(AuthType.UNNECESSARY)
@RestController
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping("/get")
    public R<PageResult<List<BlogEntity>>> getBlogs(@RequestBody ListBlogReqDTO reqDTO) {
        return R.success(blogService.getBlogs(reqDTO));
    }

    @PostMapping("/save")
    public R<Object> saveBlog(@RequestBody SaveBlogReqDTO saveBlogReqDTO) {
        blogService.saveBlog(saveBlogReqDTO);
        return R.success();
    }

    @PostMapping("/getDetail")
    public R<BlogEntity> getBlogDetail(@RequestBody GetDetailReqDTO reqDTO) {
        return R.success(blogService.getBlogDetail(reqDTO));
    }
}
