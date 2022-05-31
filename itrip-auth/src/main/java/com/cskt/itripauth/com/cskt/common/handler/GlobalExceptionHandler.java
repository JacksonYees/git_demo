package com.cskt.itripauth.com.cskt.common.handler;
import com.cskt.common.constants.ErrorCodeEnum;
import com.cskt.common.vo.ReturnResult;
import com.cskt.itripauth.com.cskt.common.exception.DaoException;
import com.cskt.itripauth.com.cskt.common.exception.ServiceException;
import com.cskt.itripauth.com.cskt.common.exception.SysException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一的异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER =  LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 捕获异常
     * @param e 异常类型为 Exception
     */
    @ExceptionHandler(value = Exception.class)
    public ReturnResult error(Exception e){
        LOGGER.error(e.getMessage(),e);
        return ReturnResult.error(ErrorCodeEnum.SYSTEM_EXECUTION_ERROR);
    }

    /**
     * 捕获异常
     * @param e 异常类型为 ServiceException (service层)
     * */
    @ExceptionHandler(value = ServiceException.class)
    public ReturnResult error(ServiceException e) {
        LOGGER.error(e.getMessage(), e);
        return ReturnResult.error(e.getErrorCode(),e.getMessage());
    }
    /**
     * 捕获异常 *
     * @param e 异常类型为 DaoException (Dao层)
     *  */
    @ExceptionHandler(value = DaoException.class)
    public ReturnResult error(DaoException e) {
        LOGGER.error(e.getMessage(), e);
        return ReturnResult.error(e.getErrorCode(),e.getMessage());
    }

    /**
     *
     * @param e 异常类型为SysException (系统)
     * @return
     */
    @ExceptionHandler(value = SysException.class)
    public ReturnResult error(SysException e) {
        LOGGER.error(e.getMessage(), e);
        return ReturnResult.error(e.getErrorCode(), e.getMessage()); }
}
