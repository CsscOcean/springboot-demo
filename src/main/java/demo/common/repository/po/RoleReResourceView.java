package demo.common.repository.po;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Builder;

/**
 * VIEW 实体类
 *
 * @author 水张哲
 * @date 2021/10/23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("v_role_re_resource")
public class RoleReResourceView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "角色资源关联id")
    private Long id;

    @Schema(description = "角色id")
    private Long roleId;

    @Schema(description = "资源id")
    private Long resourceId;

    @Schema(description = "资源名称")
    private String name;

    @Schema(description = "资源路径")
    private String url;

    @Schema(description = "资源描述")
    private String description;

    @Schema(description = "创建日期")
    private LocalDateTime createTime;

    @Schema(description = "修改日期")
    private LocalDateTime updateTime;


}
