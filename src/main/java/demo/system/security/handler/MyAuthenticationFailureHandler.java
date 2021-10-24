package demo.system.security.handler;

import com.alibaba.fastjson.JSONObject;
import demo.system.result.emun.ErrorCodeEnum;
import demo.system.result.entity.JsonResult;
import demo.system.result.entity.Result;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 身份验证失败处理程序
 *
 * @author 水张哲
 * @date 2021/10/23
 */
@Component
public class MyAuthenticationFailureHandler extends JsonResult implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        // HTTP Code
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        // JSON Data
        ErrorCodeEnum errorCodeEnum;
        if (e instanceof InternalAuthenticationServiceException) {
            // 用户账户不存在
            errorCodeEnum = ErrorCodeEnum.USER_ERROR_A0201;
        } else if (e instanceof DisabledException) {
            // 用户账户被冻结
            errorCodeEnum = ErrorCodeEnum.USER_ERROR_A0203;
        } else if (e instanceof AccountExpiredException) {
            // 账号已过期
            errorCodeEnum = ErrorCodeEnum.USER_ERROR_A0230;
        } else if (e instanceof CredentialsExpiredException) {
            // 密码已过期
            errorCodeEnum = ErrorCodeEnum.USER_ERROR_A0230;
        } else if (e instanceof LockedException) {
            // 账号已冻结
            errorCodeEnum = ErrorCodeEnum.USER_ERROR_A0202;
        } else if (e instanceof BadCredentialsException) {
            // 用户密码错误
            errorCodeEnum = ErrorCodeEnum.USER_ERROR_A0210;
        } else {
            // 用户登陆异常
            errorCodeEnum = ErrorCodeEnum.USER_ERROR_A0200;
        }

        this.writeJson(httpServletResponse, Result.error(errorCodeEnum));
    }
}
