package demo.system.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

/**
 * Swagger 配置类
 *
 * @author 水张哲
 * @date 2021/10/15
 */
@EnableOpenApi
@Configuration
public class SwaggerConfig {

    /**
     * 全局模块
     */
    @Bean
    public Docket allDocket() {
        return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo());
    }

    /**
     * 配置 Swagger 信息
     */
    private ApiInfo apiInfo() {
        // 作者信息
        Contact contact = new Contact("中船（浙江）海洋科技有限公司", "", "");
        return new ApiInfo(
                "Develop Demo",
                "软件开发中心 ( Java | Spring Boot | Spring Security | Mybatis-plus ) 开发示例",
                "v1.0.0",
                "",
                contact,
                "Apache 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>()
        );
    }
}
