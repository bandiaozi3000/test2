package com.test.exception;

/**
 * 
 * @Title: ExceptionWrapper.java
 * @Package com.bc.jiangbei.mgt.util.exception 
 * @Description: 自定义异常(业务异常)
 * @author cy.wang
 * @date 2018年10月9日 下午5:42:58 
 * @version V1.0
 *
 */
public class ExceptionWrapper extends Exception {

	private static final long serialVersionUID = -3954547921665737839L;
	private Exception exception; // 异常对象
	private String originalErrMsg; // 真实异常信息
	private String customErrMsg; // 自定义异常信息
	private String code ; // 异常编码

	public ExceptionWrapper() {
		super();
	}

	public ExceptionWrapper(String customErrMsg) {
		super();
		this.customErrMsg = customErrMsg;
	}

	public ExceptionWrapper(String code, String customErrMsg) {
		super();
		this.customErrMsg = customErrMsg;
		this.code = code;
	}

	public ExceptionWrapper(String customErrMsg, Exception exception) {
		super();
		this.originalErrMsg = exception.getMessage();
		this.customErrMsg = customErrMsg;
		this.exception = exception;
	}

	public Exception getException() {
		return exception;
	}

	public ExceptionWrapper setException(Exception exception) {
		this.exception = exception;
		return this;
	}

	public String getOriginalErrMsg() {
		return originalErrMsg;
	}

	public ExceptionWrapper setOriginalErrMsg(String originalErrMsg) {
		this.originalErrMsg = originalErrMsg;
		return this;
	}

	public String getCustomErrMsg() {
		return customErrMsg;
	}

	public ExceptionWrapper setCustomErrMsg(String customErrMsg) {
		this.customErrMsg = customErrMsg;
		return this;
	}

	@Override
	public String getMessage() {
		return (originalErrMsg == null?"":originalErrMsg)+ getCustomErrMsg();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
