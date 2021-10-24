package demo.common.service.impl;

import demo.common.repository.po.Role;
import demo.common.repository.mapper.RoleMapper;
import demo.common.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 角色 服务实现类
 *
 * @author 水张哲
 * @date 2021/10/23
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
