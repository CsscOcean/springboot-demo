package demo.system.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import demo.common.repository.po.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Security 用户
 *
 * @author 水张哲
 * @date 2021/10/19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"handler", "password", "roles", "isCredentialsNonExpired", "isAccountNonExpired", "isAccountNonLocked", "isEnabled"})
public class SecurityUser extends User implements UserDetails {

    @Schema(description = "角色列表")
    private List<SecurityRole> roles;

    public SecurityUser(User user, List<SecurityRole> roles) {
        super(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getNickname(),
                user.getIsAccountNonExpired(),
                user.getIsAccountNonExpired(),
                user.getIsCredentialsNonExpired(),
                user.getIsEnabled(),
                user.getCreateTime(),
                user.getUpdateTime()
        );
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return getIsAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return getIsAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return getIsCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return getIsEnabled();
    }
}
