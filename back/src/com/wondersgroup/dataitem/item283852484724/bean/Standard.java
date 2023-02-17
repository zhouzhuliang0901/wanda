package com.wondersgroup.dataitem.item283852484724.bean;

import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * “好差评”动态指标项
 * @author wanda
 *
 */
public class Standard {
	
	/*
	 * 分值
	 */
	@Excel(name = "分值")
	private String source;
	
	/*
	 * 类型
	 */
	@Excel(name = "类型")
	private String type;
	
	/*
	 * 编号
	 */
	@Excel(name = "编号")
	private String code;
	
	/*
	 * 内容
	 */
	@Excel(name = "内容")
	private String content;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
