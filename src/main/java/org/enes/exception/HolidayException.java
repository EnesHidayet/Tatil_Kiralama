package org.enes.exception;

import lombok.Getter;



@Getter
public class HolidayException extends RuntimeException{

    private final ErrorType errorType;

    public HolidayException(ErrorType errorType, String customMessage){
        super(customMessage);
        this.errorType = errorType;
    }
    public HolidayException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}