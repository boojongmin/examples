package com.school.vo;

public class ResultVo {
	private boolean success;
    private Object result;
    private String description;


	public ResultVo(boolean success, String description) {
		this.success = success;
		this.description = description;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
}
