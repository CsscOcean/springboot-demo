package demo.system.security.handler;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import demo.system.result.entity.JsonResult;
import demo.system.result.entity.Result;
import demo.system.security.entity.SecurityUser;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 身份验证成功处理程序
 *
 * @author 水张哲
 * @date 2021/10/23
 */
@Component
public class MyAuthenticationSuccessHandler extends JsonResult implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        // HTTP Code
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        // JSON Data
        SecurityUser user = (SecurityUser) authentication.getPrincipal();

        this.writeJson(httpServletResponse, Result.success(user));
    }
}
