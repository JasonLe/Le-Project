package com.project.leuser.dto.response;

import com.project.leuser.entity.User;
import lombok.Builder;
import lombok.Data;

/**
 * @author whl
 * @Description:
 * @date 2023/7/14
 */
@Data
@Builder
public class TokenResponse {
    private String leToken;
    private User user;
}
