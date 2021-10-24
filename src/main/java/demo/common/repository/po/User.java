package demo.common.repository.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Builder;

/**
 * 用户 实体类
 *
 * @author 水张哲
 * @date 2021/10/19
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("s_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "用户id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "用户密码")
    private String password;

    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "帐户未过期")
    private Boolean isAccountNonExpired;

    @Schema(description = "帐户未锁定")
    private Boolean isAccountNonLocked;

    @Schema(description = "凭证未过期")
    private Boolean isCredentialsNonExpired;

    @Schema(description = "已启用")
    private Boolean isEnabled;

    @Schema(description = "创建日期")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Schema(description = "修改日期")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
