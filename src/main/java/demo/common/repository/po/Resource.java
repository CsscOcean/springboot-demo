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
 * 资源 实体类
 *
 * @author 水张哲
 * @date 2021/10/23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("s_resource")
public class Resource implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "资源id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "资源名称")
    private String name;

    @Schema(description = "资源路径")
    private String url;

    @Schema(description = "资源描述")
    private String description;

    @Schema(description = "创建日期")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Schema(description = "修改日期")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
