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
@TableName("v_user_re_role")
public class UserReRoleView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "用户角色关联id")
    private Long id;

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "角色id")
    private Long roleId;

    @Schema(description = "角色标签")
    private String tag;

    @Schema(description = "角色名称")
    private String name;

    @Schema(description = "角色描述")
    private String description;

    @Schema(description = "创建日期")
    private LocalDateTime createTime;

    @Schema(description = "修改日期")
    private LocalDateTime updateTime;


}
