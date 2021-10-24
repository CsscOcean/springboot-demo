package demo.system.xss.module;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringEscapeUtils;

import java.io.IOException;

/**
 * 实现Jackson序列化方法，将参数值转义处理
 *
 * @author 水张哲
 * @date 2021/10/15
 */
@Slf4j
public class JacksonSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(StringEscapeUtils.unescapeHtml4(s));
    }
}
