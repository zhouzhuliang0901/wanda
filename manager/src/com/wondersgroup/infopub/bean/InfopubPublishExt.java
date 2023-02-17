package com.wondersgroup.infopub.bean;
		

/**
 * 设备发布
 * @author scalffold
 */
@SuppressWarnings("serial")
public class InfopubPublishExt extends InfopubPublish{
	/**
	 * mac 地址
	 */
    private String mac;
    /**
     * 发布内容
     */
    private String clContent;
	/**
	 * @return the mac
	 */
	public String getMac() {
		return mac;
	}
	/**
	 * @param mac the mac to set
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}
	/**
	 * @return the clContent
	 */
	public String getClContent() {
		return clContent;
	}
	/**
	 * @param clContent the clContent to set
	 */
	public void setClContent(String clContent) {
		this.clContent = clContent;
	}

}