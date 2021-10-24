package demo.system.security.strategy;

import com.alibaba.fastjson.JSONObject;
import demo.system.result.emun.ErrorCodeEnum;
import demo.system.result.entity.JsonResult;
import demo.system.result.entity.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 匿名用户访问资源无权限异常
 *
 * @author 水张哲
 * @date 2021/10/23
 */
@Component
public class MyAuthenticationEntryPoint extends JsonResult implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        // HTTP Code
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        this.writeJson(httpServletResponse, Result.error(ErrorCodeEnum.USER_ERROR_A0301));
    }
}
