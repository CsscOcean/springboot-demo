package demo.system.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import demo.common.repository.mapper.UserMapper;
import demo.common.repository.mapper.UserReRoleViewMapper;
import demo.common.repository.po.User;
import demo.common.repository.po.UserReRoleView;
import demo.system.security.entity.SecurityRole;
import demo.system.security.entity.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户详情服务实现
 *
 * @author 水张哲
 * @date 2021/10/19
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserReRoleViewMapper userReRoleViewMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || username.trim().length() <= 0) {
            throw new UsernameNotFoundException("请求必填参数为空");
        } else {
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.eq("username", username);
            List<User> userList = userMapper.selectList(userQueryWrapper);
            if (userList.isEmpty()) {
                throw new UsernameNotFoundException("用户账户不存在");
            } else {
                User user = userList.get(0);
                Long userId = user.getId();
                // 查询角色信息列表(查询出来的是UserReRoleView列表,这里转换成所需要的securityRole列表)
                QueryWrapper<UserReRoleView> userReRoleQueryWrapper = new QueryWrapper<>();
                userReRoleQueryWrapper.eq("user_id", userId);
                List<SecurityRole> securityRoleList = userReRoleViewMapper.selectList(userReRoleQueryWrapper)
                        .stream()
                        .map(SecurityRole::new)
                        .collect(Collectors.toList());
                return new SecurityUser(user, securityRoleList);
            }
        }
    }
}
