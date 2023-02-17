package com.wondersgroup.dataitem.item310750019000.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Message implements Serializable{
	

	// 消息头长度
	public short headLen;
	// 业务体长度
	public int bodyLen;
	// 标志位
	public byte flag;
	// 消息头
	public byte[] headbytes;
	// 业务体
	public byte[] bodybytes;
	// 数字签名
	public byte[] signbytes;
	
	
}
