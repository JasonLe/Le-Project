package com.project.lecommon.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author whl
 * @Description:
 * @date 2023/8/2
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {
    private Long pageNum;
    private Long pages;
    private Long pageTotal;
    private T data;
}
