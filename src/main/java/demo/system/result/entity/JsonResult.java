package demo.system.result.entity;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Result 统一返回标准类
 *
 * @author 水张哲
 * @date 2021/10/23
 */
public class JsonResult {

    @Resource
    private ObjectMapper objectMapper;

    protected void writeJson(
            HttpServletResponse httpServletResponse,
            Object data
    ) throws IOException {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(data));
        httpServletResponse.getWriter().flush();
        httpServletResponse.getWriter().close();
    }
}
