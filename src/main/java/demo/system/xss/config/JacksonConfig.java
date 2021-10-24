package demo.system.xss.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import demo.system.xss.module.JacksonDeserializer;
import demo.system.xss.module.JacksonSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Optional;

/**
 * Jackson 配置 XSS
 *
 * @author 水张哲
 * @date 2021/10/15
 */
@Slf4j
@Configuration
public class JacksonConfig implements WebMvcConfigurer {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        Optional<HttpMessageConverter<?>> optional = converters.stream()
                .filter(o -> o instanceof MappingJackson2HttpMessageConverter)
                .findFirst();
        if (optional.isPresent()) {
            MappingJackson2HttpMessageConverter converter = (MappingJackson2HttpMessageConverter) optional.get();
            ObjectMapper mapper = converter.getObjectMapper();
            SimpleModule simpleModule = new SimpleModule();
            simpleModule.addSerializer(String.class, new JacksonSerializer());
            simpleModule.addDeserializer(String.class, new JacksonDeserializer());
            mapper.registerModule(simpleModule);
        }
    }
}
