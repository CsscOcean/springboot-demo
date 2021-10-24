package demo.system.security.handler;

import com.alibaba.fastjson.JSONObject;
import demo.system.result.emun.ErrorCodeEnum;
import demo.system.result.entity.JsonResult;
import demo.system.result.entity.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 访问被拒绝处理程序
 *
 * @author 水张哲
 * @date 2021/10/23
 */
@Component
public class MyAccessDeniedHandler extends JsonResult implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        // HTTP Code
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);

        this.writeJson(httpServletResponse, Result.error(ErrorCodeEnum.USER_ERROR_A0320));
    }
}
