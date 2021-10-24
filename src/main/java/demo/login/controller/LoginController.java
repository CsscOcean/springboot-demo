package demo.login.controller;

import demo.login.repository.vo.LoginVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录及注销 控制器
 *
 * @author 水张哲
 * @date 2021/10/23
 */
@Tag(name = "登录及注销")
@RestController
public class LoginController {
    @Operation(summary = "登录", description = "登录", tags = {"登录及注销"})
    @PostMapping(value = "/login")
    public void login(
            @Parameter(name = "loginVO", description = "用户名密码") @RequestBody LoginVO loginVO
    ) {

    }

    @Operation(summary = "注销", description = "注销", tags = {"登录及注销"})
    @PostMapping(value = "/logout")
    public void logout() {

    }
}
