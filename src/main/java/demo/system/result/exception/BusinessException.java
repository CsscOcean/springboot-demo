package demo.system.result.exception;

import demo.system.result.emun.ErrorCodeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义业务逻辑错误抛出
 *
 * @author 水张哲
 * @date 2021/10/15
 */
@Slf4j
@Getter
@Setter
@NoArgsConstructor
public class BusinessException extends RuntimeException {

    private ErrorCodeEnum errorCodeEnum;
    private String supplement;

    public BusinessException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.toString());
        this.errorCodeEnum = errorCodeEnum;
    }

    public BusinessException(ErrorCodeEnum errorCodeEnum, String supplement) {
        super(errorCodeEnum.toString());
        this.errorCodeEnum = errorCodeEnum;
        this.supplement = supplement;
    }

    public BusinessException(ErrorCodeEnum errorCodeEnum, Throwable throwable) {
        super(errorCodeEnum.toString(), throwable);
        this.errorCodeEnum = errorCodeEnum;
    }

    public BusinessException(ErrorCodeEnum errorCodeEnum, String supplement, Throwable throwable) {
        super(errorCodeEnum.toString(), throwable);
        this.errorCodeEnum = errorCodeEnum;
        this.supplement = supplement;
    }
}
