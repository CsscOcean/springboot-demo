package demo.common.service;

import demo.common.repository.po.RoleReResourceView;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.access.ConfigAttribute;

import java.util.Collection;
import java.util.Map;

/**
 * VIEW 服务类
 *
 * @author 水张哲
 * @date 2021/10/23
 */
public interface RoleReResourceViewService extends IService<RoleReResourceView> {
    /**
     * 获取资源信息集合
     *
     * @return 资源信息集合
     */
    Map<String, Collection<ConfigAttribute>> getConfigAttributeCollection();

}
