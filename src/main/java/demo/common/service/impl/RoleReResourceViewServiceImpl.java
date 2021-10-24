package demo.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import demo.common.repository.mapper.RoleMapper;
import demo.common.repository.po.Role;
import demo.common.repository.po.RoleReResourceView;
import demo.common.repository.mapper.RoleReResourceViewMapper;
import demo.common.repository.po.User;
import demo.common.service.RoleReResourceViewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import demo.system.security.entity.SecurityResource;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * VIEW 服务实现类
 *
 * @author 水张哲
 * @date 2021/10/23
 */
@Service
public class RoleReResourceViewServiceImpl extends ServiceImpl<RoleReResourceViewMapper, RoleReResourceView> implements RoleReResourceViewService {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RoleReResourceViewMapper roleReResourceViewMapper;

    @Override
    public Map<String, Collection<ConfigAttribute>> getConfigAttributeCollection() {
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        roleQueryWrapper.select("id, tag");
        Map<Long, String> roleMap = roleMapper.selectList(roleQueryWrapper).stream().collect(Collectors.toMap(Role::getId,Role::getTag));
        Map<String, Collection<ConfigAttribute>> configAttributeMap = new HashMap<>(16);
        List<RoleReResourceView> roleReResourceViewList = roleReResourceViewMapper.selectList(null);
        roleReResourceViewList.forEach(roleResourceView -> {
            Long roleId = roleResourceView.getRoleId();
            SecurityResource securityResource = new SecurityResource(roleResourceView);
            securityResource.setRoleId(roleId);
            securityResource.setRoleTag(roleMap.get(roleId));
            String url = securityResource.getUrl();
            if (!configAttributeMap.containsKey(url)) {
                configAttributeMap.put(url, new ArrayList<>());
            }
            Collection<ConfigAttribute> configAttributes = configAttributeMap.get(url);
            if (!configAttributes.contains(securityResource)) {
                configAttributes.add(securityResource);
            }
        });
        return configAttributeMap;
    }
}
