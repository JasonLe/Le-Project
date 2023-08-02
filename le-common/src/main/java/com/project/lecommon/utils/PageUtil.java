package com.project.lecommon.utils;

/**
 * @author whl
 * @Description: 分页工具类
 * @date 2023/8/2
 */
public class PageUtil {

    public static Integer getPageNum(int pageNum, int pageSize) {
        return (pageNum - 1) * pageSize;
    }


}
