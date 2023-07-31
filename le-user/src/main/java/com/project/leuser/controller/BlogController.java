package com.project.leuser.controller;

import com.project.lecommon.result.R;
import com.project.leuser.annotation.AuthType;
import com.project.leuser.annotation.NeedAuth;
import com.project.leuser.dto.response.BlogResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author whl
 * @Description:
 * @date 2023/7/31
 */
@RequestMapping("/le-user/blogs")
@NeedAuth(AuthType.UNNECESSARY)
@RestController
public class BlogController {

    @RequestMapping("/get")
    public R<List<BlogResponse>> getBlogs(int pageNum) {
        List<BlogResponse> list = new ArrayList<>();
        if (pageNum == 1) {
            for (int i = 0; i < 10; i++) {
                list.add(BlogResponse.builder().blogId(i).title("第" + i + "个标题").pubDate(new Date()).digest(getDigest()).build());
            }
        } else if (pageNum == 2) {
            for (int i = 0; i < 5; i++) {
                list.add(BlogResponse.builder().blogId(i).title("第" + i + "个标题").pubDate(new Date()).digest(getDigest()).build());
            }
        }
        return R.success(list);
    }

    String getDigest() {
        StringBuilder digest = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            digest.append("这是一个内容");
        }
        return digest.toString();
    }

}
