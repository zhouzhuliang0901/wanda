package com.wondersgroup.outdevicestatus.bean;
		
import java.io.*;
import java.math.*;
import java.sql.*;
import java.text.*;
import javax.persistence.*;
import javax.xml.bind.annotation.adapters.*;
import org.apache.commons.lang.builder.*;

import reindeer.base.utils.StringUtil;
import wfc.facility.tool.autocode.*;
import wfc.service.util.*;

/**
 * 设备信息
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "INFOPUB_DEVICE_INFO")
public class InfopubDeviceInfo implements Serializable {
    
    /**
     * 设备信息
     */
    public static final String INFOPUB_DEVICE_INFO = "INFOPUB_DEVICE_INFO";
    
    /**
     * 设备ID
     */
    public static final String ST_DEVICE_ID = "ST_DEVICE_ID";
    
    /**
     * 设备名称
     */
    public static final String ST_DEVICE_NAME = "ST_DEVICE_NAME";
    
    /**
     * 设备编号
     */
    public static final String ST_DEVICE_CODE = "ST_DEVICE_CODE";
    
    /**
     * 设备IP
     */
    public static final String ST_DEVICE_IP = "ST_DEVICE_IP";
    
    /**
     * 设备MAC
     */
    public static final String ST_DEVICE_MAC = "ST_DEVICE_MAC";
    
    /**
     * 设备详细地址
     */
    public static final String ST_DEVICE_ADDRESS = "ST_DEVICE_ADDRESS";
    
    /**
     * 类型ID
     */
    public static final String ST_TYPE_ID = "ST_TYPE_ID";
    
    /**
     * 是否是主机
     */
    public static final String NM_IS_HOST = "NM_IS_HOST";
    
    /**
     * 排序
     */
    public static final String NM_ORDER = "NM_ORDER";
    
    /**
     * 监控间隔
     */
    public static final String NM_INTERVAL = "NM_INTERVAL";
    
    /**
     * 是否恢复
     */
    public static final String NM_RECOVER = "NM_RECOVER";
    
    /**
     * 故障尝试次数
     */
    public static final String NM_DOWN_TRY = "NM_DOWN_TRY";
    
    /**
     * 是否通知
     */
    public static final String NM_NOTIFICATION = "NM_NOTIFICATION";
    
    /**
     * 经度
     */
    public static final String NM_LNG = "NM_LNG";
    
    /**
     * 纬度
     */
    public static final String NM_LAT = "NM_LAT";
    
    /**
     * 是否在线
     */
    public static final String NM_ONLINE = "NM_ONLINE";
    
    /**
     * 消息通道
     */
    public static final String ST_CHANNEL = "ST_CHANNEL";
    
    /**
     * 终端配置
     */
    public static final String ST_CONFIG_ID = "ST_CONFIG_ID";
    
    /**
     * 用户ID
     */
    public static final String ST_USER_ID = "ST_USER_ID";
    
    /**
     * 区域ID
     */
    public static final String ST_AREA_ID = "ST_AREA_ID";
    
    /**
     * 创建时间
     */
    public static final String DT_CREATE = "DT_CREATE";
    
    /**
     * 更新时间
     */
    public static final String DT_UPDATE = "DT_UPDATE";
    
    /**
     * 备注
     */
    public static final String ST_DESC = "ST_DESC";
    
    /**
     * 证书唯一标识
     */
    public static final String ST_CERT_KEY = "ST_CERT_KEY";
    
    public InfopubDeviceInfo() {
    }
    
    /**
     * 设备ID
     */
    @Id
    @Column(name = "ST_DEVICE_ID")
    private String stDeviceId;
    
    /**
     * 设备名称
     */
    @Column(name = "ST_DEVICE_NAME")
    private String stDeviceName;
    
    /**
     * 设备编号
     */
    @Column(name = "ST_DEVICE_CODE")
    private String stDeviceCode;
    
    /**
     * 设备IP
     */
    @Column(name = "ST_DEVICE_IP")
    private String stDeviceIp;
    
    /**
     * 设备MAC
     */
    @Column(name = "ST_DEVICE_MAC")
    private String stDeviceMac;
    
    /**
     * 设备详细地址
     */
    @Column(name = "ST_DEVICE_ADDRESS")
    private String stDeviceAddress;
    
    /**
     * 类型ID
     */
    @Column(name = "ST_TYPE_ID")
    private String stTypeId;
    
    /**
     * 是否是主机
     */
    @Column(name = "NM_IS_HOST")
    private BigDecimal nmIsHost;
    
    /**
     * 排序
     */
    @Column(name = "NM_ORDER")
    private BigDecimal nmOrder;
    
    /**
     * 监控间隔
     */
    @Column(name = "NM_INTERVAL")
    private BigDecimal nmInterval;
    
    /**
     * 是否恢复
     */
    @Column(name = "NM_RECOVER")
    private BigDecimal nmRecover;
    
    /**
     * 故障尝试次数
     */
    @Column(name = "NM_DOWN_TRY")
    private BigDecimal nmDownTry;
    
    /**
     * 是否通知
     */
    @Column(name = "NM_NOTIFICATION")
    private BigDecimal nmNotification;
    
    /**
     * 经度
     */
    @Column(name = "NM_LNG")
    private BigDecimal nmLng;
    
    /**
     * 纬度
     */
    @Column(name = "NM_LAT")
    private BigDecimal nmLat;
    
    /**
     * 是否在线
     */
    @Column(name = "NM_ONLINE")
    private BigDecimal nmOnline;
    
    /**
     * 消息通道
     */
    @Column(name = "ST_CHANNEL")
    private String stChannel;
    
    /**
     * 终端配置
     */
    @Column(name = "ST_CONFIG_ID")
    private String stConfigId;
    
    /**
     * 用户ID
     */
    @Column(name = "ST_USER_ID")
    private String stUserId;
    
    /**
     * 区域ID
     */
    @Column(name = "ST_AREA_ID")
    private String stAreaId;
    /**
     * 证书唯一标识
     */
    @Column(name = "ST_CERT_KEY")
    private String stCertKey;
    
    /**
     * 创建时间
     */
    @Column(name = "DT_CREATE")
    private Timestamp dtCreate;
    
    /**
     * 更新时间
     */
    @Column(name = "DT_UPDATE")
    private Timestamp dtUpdate;
    
    /**
     * 备注
     */
    @Column(name = "ST_DESC")
    private String stDesc;
    
	/**
     * 设备ID
     */
    public String getStDeviceId() {
        return this.stDeviceId;
    }
    
    /**
     * 设备ID
     */
    public String stDeviceId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDeviceId);
    }

    /**
     * 设备ID
     */
    public void setStDeviceId(String stDeviceId) {
        stDeviceId = StringUtil.substringBySize(stDeviceId, 50, "GB18030");
        this.stDeviceId = stDeviceId;
    }
    
	/**
     * 设备名称
     */
    public String getStDeviceName() {
        return this.stDeviceName;
    }
    
    /**
     * 设备名称
     */
    public String stDeviceName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDeviceName);
    }

    /**
     * 设备名称
     */
    public void setStDeviceName(String stDeviceName) {
        stDeviceName = StringUtil.substringBySize(stDeviceName, 100, "GB18030");
        this.stDeviceName = stDeviceName;
    }
    
	/**
     * 设备编号
     */
    public String getStDeviceCode() {
        return this.stDeviceCode;
    }
    
    /**
     * 设备编号
     */
    public String stDeviceCode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDeviceCode);
    }

    /**
     * 设备编号
     */
    public void setStDeviceCode(String stDeviceCode) {
        stDeviceCode = StringUtil.substringBySize(stDeviceCode, 50, "GB18030");
        this.stDeviceCode = stDeviceCode;
    }
    
	/**
     * 设备IP
     */
    public String getStDeviceIp() {
        return this.stDeviceIp;
    }
    
    /**
     * 设备IP
     */
    public String stDeviceIp2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDeviceIp);
    }

    /**
     * 设备IP
     */
    public void setStDeviceIp(String stDeviceIp) {
        stDeviceIp = StringUtil.substringBySize(stDeviceIp, 50, "GB18030");
        this.stDeviceIp = stDeviceIp;
    }
    
	/**
     * 设备MAC
     */
    public String getStDeviceMac() {
        return this.stDeviceMac;
    }
    
    /**
     * 设备MAC
     */
    public String stDeviceMac2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDeviceMac);
    }

    /**
     * 设备MAC
     */
    public void setStDeviceMac(String stDeviceMac) {
        stDeviceMac = StringUtil.substringBySize(stDeviceMac, 50, "GB18030");
        this.stDeviceMac = stDeviceMac;
    }
    
	/**
     * 设备详细地址
     */
    public String getStDeviceAddress() {
        return this.stDeviceAddress;
    }
    
    /**
     * 设备详细地址
     */
    public String stDeviceAddress2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDeviceAddress);
    }

    /**
     * 设备详细地址
     */
    public void setStDeviceAddress(String stDeviceAddress) {
        stDeviceAddress = StringUtil.substringBySize(stDeviceAddress, 100, "GB18030");
        this.stDeviceAddress = stDeviceAddress;
    }
    
	/**
     * 类型ID
     */
    public String getStTypeId() {
        return this.stTypeId;
    }
    
    /**
     * 类型ID
     */
    public String stTypeId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stTypeId);
    }

    /**
     * 类型ID
     */
    public void setStTypeId(String stTypeId) {
        stTypeId = StringUtil.substringBySize(stTypeId, 50, "GB18030");
        this.stTypeId = stTypeId;
    }

	/**
     * 是否是主机
     */
    public BigDecimal getNmIsHost() {
        return this.nmIsHost;
    }
    
    /**
     * 是否是主机
     */
    public String nmIsHost2Html(int precision) {
        if (this.nmIsHost == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmIsHost);
        }
    }

    /**
     * 是否是主机
     */
    public void setNmIsHost(BigDecimal nmIsHost) {
        this.nmIsHost = nmIsHost;
    }

	/**
     * 排序
     */
    public BigDecimal getNmOrder() {
        return this.nmOrder;
    }
    
    /**
     * 排序
     */
    public String nmOrder2Html(int precision) {
        if (this.nmOrder == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmOrder);
        }
    }

    /**
     * 排序
     */
    public void setNmOrder(BigDecimal nmOrder) {
        this.nmOrder = nmOrder;
    }

	/**
     * 监控间隔
     */
    public BigDecimal getNmInterval() {
        return this.nmInterval;
    }
    
    /**
     * 监控间隔
     */
    public String nmInterval2Html(int precision) {
        if (this.nmInterval == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmInterval);
        }
    }

    /**
     * 监控间隔
     */
    public void setNmInterval(BigDecimal nmInterval) {
        this.nmInterval = nmInterval;
    }

	/**
     * 是否恢复
     */
    public BigDecimal getNmRecover() {
        return this.nmRecover;
    }
    
    /**
     * 是否恢复
     */
    public String nmRecover2Html(int precision) {
        if (this.nmRecover == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmRecover);
        }
    }

    /**
     * 是否恢复
     */
    public void setNmRecover(BigDecimal nmRecover) {
        this.nmRecover = nmRecover;
    }

	/**
     * 故障尝试次数
     */
    public BigDecimal getNmDownTry() {
        return this.nmDownTry;
    }
    
    /**
     * 故障尝试次数
     */
    public String nmDownTry2Html(int precision) {
        if (this.nmDownTry == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmDownTry);
        }
    }

    /**
     * 故障尝试次数
     */
    public void setNmDownTry(BigDecimal nmDownTry) {
        this.nmDownTry = nmDownTry;
    }

	/**
     * 是否通知
     */
    public BigDecimal getNmNotification() {
        return this.nmNotification;
    }
    
    /**
     * 是否通知
     */
    public String nmNotification2Html(int precision) {
        if (this.nmNotification == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmNotification);
        }
    }

    /**
     * 是否通知
     */
    public void setNmNotification(BigDecimal nmNotification) {
        this.nmNotification = nmNotification;
    }

	/**
     * 经度
     */
    public BigDecimal getNmLng() {
        return this.nmLng;
    }
    
    /**
     * 经度
     */
    public String nmLng2Html(int precision) {
        if (this.nmLng == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmLng);
        }
    }

    /**
     * 经度
     */
    public void setNmLng(BigDecimal nmLng) {
        this.nmLng = nmLng;
    }

	/**
     * 纬度
     */
    public BigDecimal getNmLat() {
        return this.nmLat;
    }
    
    /**
     * 纬度
     */
    public String nmLat2Html(int precision) {
        if (this.nmLat == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmLat);
        }
    }

    /**
     * 纬度
     */
    public void setNmLat(BigDecimal nmLat) {
        this.nmLat = nmLat;
    }

	/**
     * 是否在线
     */
    public BigDecimal getNmOnline() {
        return this.nmOnline;
    }
    
    /**
     * 是否在线
     */
    public String nmOnline2Html(int precision) {
        if (this.nmOnline == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmOnline);
        }
    }

    /**
     * 是否在线
     */
    public void setNmOnline(BigDecimal nmOnline) {
        this.nmOnline = nmOnline;
    }
    
	/**
     * 消息通道
     */
    public String getStChannel() {
        return this.stChannel;
    }
    
    /**
     * 消息通道
     */
    public String stChannel2Html() {
        return StringHelper.replaceHTMLSymbol(this.stChannel);
    }

    /**
     * 消息通道
     */
    public void setStChannel(String stChannel) {
        stChannel = StringUtil.substringBySize(stChannel, 50, "GB18030");
        this.stChannel = stChannel;
    }
    
	/**
     * 终端配置
     */
    public String getStConfigId() {
        return this.stConfigId;
    }
    
    /**
     * 终端配置
     */
    public String stConfigId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stConfigId);
    }

    /**
     * 终端配置
     */
    public void setStConfigId(String stConfigId) {
        stConfigId = StringUtil.substringBySize(stConfigId, 50, "GB18030");
        this.stConfigId = stConfigId;
    }
    
	/**
     * 用户ID
     */
    public String getStUserId() {
        return this.stUserId;
    }
    
    /**
     * 用户ID
     */
    public String stUserId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stUserId);
    }

    /**
     * 用户ID
     */
    public void setStUserId(String stUserId) {
        stUserId = StringUtil.substringBySize(stUserId, 50, "GB18030");
        this.stUserId = stUserId;
    }
    
	/**
     * 区域ID
     */
    public String getStAreaId() {
        return this.stAreaId;
    }
    
    /**
     * 区域ID
     */
    public String stAreaId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stAreaId);
    }

    /**
     * 区域ID
     */
    public void setStAreaId(String stAreaId) {
        stAreaId = StringUtil.substringBySize(stAreaId, 50, "GB18030");
        this.stAreaId = stAreaId;
    }

	/**
     * 创建时间
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtCreate() {
        return this.dtCreate;
    }
    
    /**
     * 创建时间
     */
    public String dtCreate2Html(String pattern) {
        if (this.dtCreate == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtCreate);
        }
    }

    /**
     * 创建时间
     */
    public void setDtCreate(Timestamp dtCreate) {
        this.dtCreate = dtCreate;
    }

	/**
     * 更新时间
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtUpdate() {
        return this.dtUpdate;
    }
    
    /**
     * 更新时间
     */
    public String dtUpdate2Html(String pattern) {
        if (this.dtUpdate == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtUpdate);
        }
    }

    /**
     * 更新时间
     */
    public void setDtUpdate(Timestamp dtUpdate) {
        this.dtUpdate = dtUpdate;
    }
    
	/**
     * 备注
     */
    public String getStDesc() {
        return this.stDesc;
    }
    
    /**
     * 备注
     */
    public String stDesc2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDesc);
    }

    /**
     * 备注
     */
    public void setStDesc(String stDesc) {
        stDesc = StringUtil.substringBySize(stDesc, 100, "GB18030");
        this.stDesc = stDesc;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

	public String getStCertKey() {
		return stCertKey;
	}

	public void setStCertKey(String stCertKey) {
		this.stCertKey = stCertKey;
	}

}