package demo.system.xss.module;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringEscapeUtils;

import java.io.IOException;

/**
 * 实现Jackson反序列化方法，将参数值转义处理
 *
 * @author 水张哲
 * @date 2021/10/15
 */
@Slf4j
public class JacksonDeserializer extends JsonDeserializer<String> {

    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return StringEscapeUtils.escapeHtml4(jsonParser.getText());
    }
}
