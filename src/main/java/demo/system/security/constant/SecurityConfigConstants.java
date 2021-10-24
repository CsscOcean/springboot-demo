package demo.system.security.constant;

/**
 * Security 配置类常量
 *
 * @author 水张哲
 * @date 2021/10/23
 */
public class SecurityConfigConstants {

    /**
     * Swagger Whitelist
     */
    public static final String[] SWAGGER_WHITELIST = {
            "/swagger-ui/*",
            "/swagger-resources/**",
            "/v3/api-docs",
            "/webjars/**",
            "/doc.html"
    };

    /**
     * Druid Whitelist
     */
    public static final String[] DRUID_WHITELIST = {
            "/druid/**"
    };

    /**
     * System Whitelist
     */
    public static final String[] SYSTEM_WHITELIST = {

    };

    private SecurityConfigConstants() {
        throw new IllegalStateException("Cannot create instance of static util class");
    }
}
