package demo.login.repository.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录实体
 *
 * @author 水张哲
 * @date 2021/10/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginVO {
    @Schema(description = "用户名", required = true)
    private String username;
    @Schema(description = "密码", required = true)
    private String password;
}
