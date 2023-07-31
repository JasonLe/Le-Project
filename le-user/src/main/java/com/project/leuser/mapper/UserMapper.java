package com.project.leuser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.leuser.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author whl
 * @Description:
 * @date 2023/7/12
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
