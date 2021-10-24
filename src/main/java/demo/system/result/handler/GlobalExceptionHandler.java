package demo.system.result.handler;

import demo.system.result.entity.Result;
import demo.system.result.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author 水张哲
 * @date 2021/10/15
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常处理
     *
     * @param businessException 业务异常
     * @return 统一返回标准类
     */
    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<Result<String>> businessExceptionHandler(BusinessException businessException) {
        return ResponseEntity.status(HttpStatus.OK).body(Result.error(businessException));
    }

    /// 异常处理待补充...
}
