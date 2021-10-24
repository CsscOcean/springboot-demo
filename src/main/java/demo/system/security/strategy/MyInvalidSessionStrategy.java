package demo.system.security.strategy;

import com.alibaba.fastjson.JSONObject;
import demo.system.result.emun.ErrorCodeEnum;
import demo.system.result.entity.JsonResult;
import demo.system.result.entity.Result;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 无效会话策略
 * 如：用账号在另一处登录
 *
 * @author 水张哲
 * @date 2021/10/23
 */
@Component
public class MyInvalidSessionStrategy extends JsonResult implements InvalidSessionStrategy {

    @Override
    public void onInvalidSessionDetected(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        // HTTP Code
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        this.writeJson(httpServletResponse, Result.error(ErrorCodeEnum.USER_ERROR_A0230));
    }
}
