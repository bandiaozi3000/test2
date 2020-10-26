package com.test.exception;


public class ExceptionWrapper2 extends Exception {
    private static final long serialVersionUID = -3954547921665737839L;
    private Exception exception;
    private String originalErrMsg;
    private String customErrMsg;
    private String code;

    public ExceptionWrapper2() {
        this.code = ApiCallResult.FAILURE.getCode();
    }

    public ExceptionWrapper2(String customErrMsg) {
        this.code = ApiCallResult.FAILURE.getCode();
        this.customErrMsg = customErrMsg;
    }

    public ExceptionWrapper2(String code, String customErrMsg) {
        this.code = ApiCallResult.FAILURE.getCode();
        this.customErrMsg = customErrMsg;
        this.code = code;
    }

    public ExceptionWrapper2(String customErrMsg, Exception exception) {
        this.code = ApiCallResult.FAILURE.getCode();
        this.originalErrMsg = exception.getMessage();
        this.customErrMsg = customErrMsg;
        this.exception = exception;
    }

    public ExceptionWrapper2(ApiCallResult apiCallResult) {
        this.code = ApiCallResult.FAILURE.getCode();
        this.originalErrMsg = apiCallResult.getDesc();
        this.code = apiCallResult.getCode();
    }

    public Exception getException() {
        return this.exception;
    }

    public ExceptionWrapper2 setException(Exception exception) {
        this.exception = exception;
        return this;
    }

    public String getOriginalErrMsg() {
        return this.originalErrMsg;
    }

    public ExceptionWrapper2 setOriginalErrMsg(String originalErrMsg) {
        this.originalErrMsg = originalErrMsg;
        return this;
    }

    public String getCustomErrMsg() {
        return this.customErrMsg;
    }

    public ExceptionWrapper2 setCustomErrMsg(String customErrMsg) {
        this.customErrMsg = customErrMsg;
        return this;
    }

    public String getMessage() {
        return (originalErrMsg == null?"":originalErrMsg)+ getCustomErrMsg();
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
