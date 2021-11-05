package demo.system.security.handler;

import demo.system.result.entity.JsonResult;
import demo.system.result.entity.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注销成功处理程序
 *
 * @author 水张哲
 * @date 2021/10/23
 */
@Component
public class MyLogoutSuccessHandler extends JsonResult implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        // HTTP Code
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);

        this.writeJson(httpServletResponse, Result.success());
    }
}
