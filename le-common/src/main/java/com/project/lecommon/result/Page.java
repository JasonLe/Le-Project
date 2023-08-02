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
public class Page<T> {
    private Integer pageNum;
    private Integer pageSize;
    private Integer pageTotal;
    private T data;
}
