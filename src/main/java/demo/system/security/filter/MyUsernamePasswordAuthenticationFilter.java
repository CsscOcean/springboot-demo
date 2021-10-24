package demo.system.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.system.result.emun.ErrorCodeEnum;
import demo.system.result.exception.BusinessException;
import lombok.val;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * 自定义登录认证处理器
 *
 * @author 水张哲
 * @date 2021/10/23
 */
public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        ObjectMapper objectMapper = new ObjectMapper();
        String username;
        String password;
        try (InputStream is = request.getInputStream()) {
            val authenticationBean = objectMapper.readValue(is, Map.class);
            username = (String) authenticationBean.get("username");
            password = (String) authenticationBean.get("password");
        } catch (IOException e) {
            throw new BusinessException(ErrorCodeEnum.USER_ERROR_A0410, e);
        }
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
