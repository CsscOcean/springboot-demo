package demo.system.security.manager;

import demo.common.service.ResourceService;
import demo.common.service.RoleReResourceViewService;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Map;

/**
 * 自定义资源筛选器
 *
 * @author 水张哲
 * @date 2021/10/23
 */
@Component
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Resource
    private RoleReResourceViewService roleReResourceViewService;

    private Map<String, Collection<ConfigAttribute>> configAttributeMap = null;

    private void loadResourceDefine() {
        configAttributeMap = roleReResourceViewService.getConfigAttributeCollection();
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        AntPathRequestMatcher antPathMatcher;
        FilterInvocation filterInvocation = (FilterInvocation) o;
        HttpServletRequest request = filterInvocation.getRequest();
        if (configAttributeMap == null) {
            loadResourceDefine();
        }
        assert configAttributeMap != null;
        for (String url : configAttributeMap.keySet()) {
            antPathMatcher = new AntPathRequestMatcher(url);
            if (antPathMatcher.matches(request)) {
                return configAttributeMap.get(url);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
