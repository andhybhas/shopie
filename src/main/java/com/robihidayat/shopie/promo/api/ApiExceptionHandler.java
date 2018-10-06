package com.robihidayat.shopie.promo.api;

import com.robihidayat.shopie.promo.model.BaseResponse;
import com.robihidayat.shopie.promo.exception.ValidationApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(ValidationApiException.class)
    public ResponseEntity<BaseResponse> handleValidationException(ValidationApiException ex) {
        log.warn("Validation API exception caught : {}", ex.getMessage());
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setResponseCode(ex.getResponseCode().value());
        baseResponse.setResponseMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(baseResponse);
    }
}
