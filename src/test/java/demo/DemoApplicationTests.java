package demo;

import demo.common.service.impl.RoleReResourceViewServiceImpl;
import demo.system.security.service.impl.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DemoApplicationTests {
    @Resource
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @Resource
    private RoleReResourceViewServiceImpl roleReResourceViewServiceImpl;

    @Test
    void contextLoads() {
        System.out.println(userDetailsServiceImpl.loadUserByUsername("admin").toString());

    }

    @Test
    void contextLoads2() {
        System.out.println(roleReResourceViewServiceImpl.getConfigAttributeCollection());
    }
}
