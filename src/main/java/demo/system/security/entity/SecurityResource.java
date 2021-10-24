package demo.system.security.entity;

import demo.common.repository.po.Resource;
import demo.common.repository.po.RoleReResourceView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.access.ConfigAttribute;

/**
 * Security 资源
 *
 * @author 水张哲
 * @date 2021/10/23
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SecurityResource extends Resource implements ConfigAttribute {

    @Schema(description = "角色Id")
    private Long roleId;
    @Schema(description = "角色标签")
    private String roleTag;

    @Override
    public String getAttribute() {
        return roleTag;
    }

    public SecurityResource(RoleReResourceView roleReResourceView) {
        super(
                roleReResourceView.getResourceId(),
                roleReResourceView.getName(),
                roleReResourceView.getUrl(),
                roleReResourceView.getDescription(),
                roleReResourceView.getCreateTime(),
                roleReResourceView.getUpdateTime()
        );
    }
}
