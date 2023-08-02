package com.example.leblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.leblog.entity.BlogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author whl
 * @Description:
 * @date 2023/8/2
 */
@Mapper
public interface BlogMapper extends BaseMapper<BlogEntity> {
}
