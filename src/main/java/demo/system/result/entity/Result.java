package demo.system.result.entity;

import demo.system.result.emun.ErrorCodeEnum;
import demo.system.result.exception.BusinessException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Result 统一返回标准类
 *
 * @author 水张哲
 * @date 2021/10/15
 */
@Data
@NoArgsConstructor
public class Result<T> {

    @Schema(description = "回应码")
    private String code;
    @Schema(description = "回应信息")
    private String message;
    @Schema(description = "回应数据")
    private T data;
    @Schema(description = "是否成功")
    private Boolean success;

    public Result(ErrorCodeEnum errorCodeEnum) {
        this.code = errorCodeEnum.getCode();
        this.message = errorCodeEnum.getDescription();
    }

    public static Result<Void> success() {
        Result<Void> result = new Result<>(ErrorCodeEnum.SUCCESS);
        result.setSuccess(true);
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>(ErrorCodeEnum.SUCCESS);
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    public static Result<Void> error(ErrorCodeEnum errorCodeEnum) {
        Result<Void> result = new Result<>(errorCodeEnum);
        result.setSuccess(false);
        return result;
    }

    public static Result<String> error(ErrorCodeEnum errorCodeEnum, String message) {
        Result<String> result = new Result<>(errorCodeEnum);
        result.setData(message);
        result.setSuccess(false);
        return result;
    }

    public static Result<String> error(BusinessException businessException) {
        Result<String> result = new Result<>(businessException.getErrorCodeEnum());
        result.setData(businessException.getSupplement());
        result.setSuccess(false);
        return result;
    }
}
