package com.tang.dynamic.xmlloader.exception;

/**
 * @author jiejT
 * @ClassName: LoadXMLJobException
 * @description: 加载异常类
 * @date 2022/2/23
 */
public class LoadXMLJobException extends Exception {

    public LoadXMLJobException(){
        super();
    }
    public LoadXMLJobException(String message){
        super(message);
    }
    public LoadXMLJobException(String message, Throwable cause){
        super(message,cause);
    }
}
