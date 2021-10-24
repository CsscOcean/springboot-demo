package demo.system.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import demo.common.repository.po.Role;
import demo.common.repository.po.UserReRoleView;
import org.springframework.security.core.GrantedAuthority;

/**
 * Security 角色
 *
 * @author 水张哲
 * @date 2021/10/19
 */
@JsonIgnoreProperties("authority")
public class SecurityRole extends Role implements GrantedAuthority {

    @Override
    public String getAuthority() {
        return getTag();
    }

    public SecurityRole(UserReRoleView userReRoleView) {
        super(
                userReRoleView.getRoleId(),
                userReRoleView.getTag(),
                userReRoleView.getName(),
                userReRoleView.getDescription(),
                userReRoleView.getCreateTime(),
                userReRoleView.getUpdateTime()
        );
    }
}
