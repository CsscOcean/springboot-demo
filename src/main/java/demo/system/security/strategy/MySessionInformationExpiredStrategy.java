package demo.system.security.strategy;

import com.alibaba.fastjson.JSONObject;
import demo.system.result.emun.ErrorCodeEnum;
import demo.system.result.entity.JsonResult;
import demo.system.result.entity.Result;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 会话信息过期策略
 *
 * @author 水张哲
 * @date 2021/10/23
 */
@Component
public class MySessionInformationExpiredStrategy extends JsonResult implements SessionInformationExpiredStrategy {

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException {
        // HTTP Code
        sessionInformationExpiredEvent.getResponse().setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        this.writeJson(sessionInformationExpiredEvent.getResponse(), Result.error(ErrorCodeEnum.USER_ERROR_A0230));
    }
}
