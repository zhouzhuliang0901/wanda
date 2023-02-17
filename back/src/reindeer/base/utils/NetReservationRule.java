package reindeer.base.utils;
		
import java.io.*;
import java.math.*;
import java.text.*;
import javax.persistence.*;
import org.apache.commons.lang.builder.*;
import reindeer.base.utils.StringUtil;
import wfc.service.util.*;

/**
 * 预约规则设置-办理点事项
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "NET_RESERVATION_RULE")
public class NetReservationRule implements Serializable {
    
    /**
     * 预约规则设置-办理点事项
     */
    public static final String NET_RESERVATION_RULE = "NET_RESERVATION_RULE";
    
    /**
     * 预约规则ID
     */
    public static final String ST_ITEM_RULE_ID = "ST_ITEM_RULE_ID";
    
    /**
     * 事项编码
     */
    public static final String ST_ITEM_CODE = "ST_ITEM_CODE";
    
    /**
     * 事项名称
     */
    public static final String ST_ITEM_NAME = "ST_ITEM_NAME";
    
    /**
     * 办理点ID
     */
    public static final String ST_PLACE_ID = "ST_PLACE_ID";
    
    /**
     * 办理点
     */
    public static final String ST_PLACE_NAME = "ST_PLACE_NAME";
    
    /**
     * 预约天数
     */
    public static final String ST_RESERVATION_DAY = "ST_RESERVATION_DAY";
    
    /**
     * 预约提前天数
     */
    public static final String ST_RESERVATION_EARLY_DAY = "ST_RESERVATION_EARLY_DAY";
    
    /**
     * 星期一是否能预约
     */
    public static final String NM_MONDAY = "NM_MONDAY";
    
    /**
     * 星期二是否能预约
     */
    public static final String NM_TUESDAY = "NM_TUESDAY";
    
    /**
     * 星期三是否能预约
     */
    public static final String NM_WEDNESDAY = "NM_WEDNESDAY";
    
    /**
     * 星期四是否能预约
     */
    public static final String NM_THURSDAY = "NM_THURSDAY";
    
    /**
     * 星期五是否能预约
     */
    public static final String NM_FRIDAY = "NM_FRIDAY";
    
    /**
     * 星期六是否能预约
     */
    public static final String NM_SATURDAY = "NM_SATURDAY";
    
    /**
     * 星期日是否能预约
     */
    public static final String NM_SUNDAY = "NM_SUNDAY";
    
    /**
     * 优先规则
     */
    public static final String NM_IS_PRIORITY = "NM_IS_PRIORITY";
    
    /**
     * 排序
     */
    public static final String NM_ORDER = "NM_ORDER";
    
    /**
     * 部门ID
     */
    public static final String NM_ORGAN_NODE_ID = "NM_ORGAN_NODE_ID";
    
    /**
     * 扩展字段1
     */
    public static final String ST_EXT1 = "ST_EXT1";
    
    /**
     * 扩展字段2
     */
    public static final String ST_EXT2 = "ST_EXT2";
    
    public NetReservationRule() {
    }
    
    /**
     * 预约规则ID
     */
    @Id
    @Column(name = "ST_ITEM_RULE_ID")
    private String stItemRuleId;
    
    /**
     * 事项编码
     */
    @Column(name = "ST_ITEM_CODE")
    private String stItemCode;
    
    /**
     * 事项名称
     */
    @Column(name = "ST_ITEM_NAME")
    private String stItemName;
    
    /**
     * 办理点ID
     */
    @Column(name = "ST_PLACE_ID")
    private String stPlaceId;
    
    /**
     * 办理点name
     */
    @Column(name = "ST_PLACE_NAME")
    private String stPlaceName;
    
    /**
     * 预约天数
     */
    @Column(name = "ST_RESERVATION_DAY")
    private BigDecimal stReservationDay;
    
    /**
     * 预约提前天数
     */
    @Column(name = "ST_RESERVATION_EARLY_DAY")
    private BigDecimal stReservationEarlyDay;
    
    /**
     * 星期一是否能预约
     */
    @Column(name = "NM_MONDAY")
    private BigDecimal nmMonday;
    
    /**
     * 星期二是否能预约
     */
    @Column(name = "NM_TUESDAY")
    private BigDecimal nmTuesday;
    
    /**
     * 星期三是否能预约
     */
    @Column(name = "NM_WEDNESDAY")
    private BigDecimal nmWednesday;
    
    /**
     * 星期四是否能预约
     */
    @Column(name = "NM_THURSDAY")
    private BigDecimal nmThursday;
    
    /**
     * 星期五是否能预约
     */
    @Column(name = "NM_FRIDAY")
    private BigDecimal nmFriday;
    
    /**
     * 星期六是否能预约
     */
    @Column(name = "NM_SATURDAY")
    private BigDecimal nmSaturday;
    
    /**
     * 星期日是否能预约
     */
    @Column(name = "NM_SUNDAY")
    private BigDecimal nmSunday;
    
    /**
     * 优先规则
     */
    @Column(name = "NM_IS_PRIORITY")
    private BigDecimal nmIsPriority;
    
    /**
     * 排序
     */
    @Column(name = "NM_ORDER")
    private BigDecimal nmOrder;
    
    /**
     * 部门ID
     */
    @Column(name = "NM_ORGAN_NODE_ID")
    private BigDecimal nmOrganNodeId;
    
    /**
     * 扩展字段1
     */
    @Column(name = "ST_EXT1")
    private String stExt1;
    
    /**
     * 扩展字段2
     */
    @Column(name = "ST_EXT2")
    private String stExt2;
    
	/**
     * 预约规则ID
     */
    public String getStItemRuleId() {
        return this.stItemRuleId;
    }
    
    /**
     * 预约规则ID
     */
    public String stItemRuleId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemRuleId);
    }

    /**
     * 预约规则ID
     */
    public void setStItemRuleId(String stItemRuleId) {
        stItemRuleId = StringUtil.substringBySize(stItemRuleId, 50, "GB18030");
        this.stItemRuleId = stItemRuleId;
    }
    
	/**
     * 事项编码
     */
    public String getStItemCode() {
        return this.stItemCode;
    }
    
    /**
     * 事项编码
     */
    public String stItemCode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemCode);
    }

    /**
     * 事项编码
     */
    public void setStItemCode(String stItemCode) {
        stItemCode = StringUtil.substringBySize(stItemCode, 50, "GB18030");
        this.stItemCode = stItemCode;
    }
    
	/**
     * 事项名称
     */
    public String getStItemName() {
        return this.stItemName;
    }
    
    /**
     * 事项名称
     */
    public String stItemName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemName);
    }

    /**
     * 事项名称
     */
    public void setStItemName(String stItemName) {
        stItemName = StringUtil.substringBySize(stItemName, 200, "GB18030");
        this.stItemName = stItemName;
    }
    
	/**
     * 办理点ID
     */
    public String getStPlaceId() {
        return this.stPlaceId;
    }
    
    /**
     * 办理点ID
     */
    public String stPlaceId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stPlaceId);
    }

    /**
     * 办理点ID
     */
    public void setStPlaceId(String stPlaceId) {
        stPlaceId = StringUtil.substringBySize(stPlaceId, 50, "GB18030");
        this.stPlaceId = stPlaceId;
    }

	/**
     * 办理点name
     */
    public String getStPlaceName() {
        return this.stPlaceName;
    }
    
    /**
     * 办理点name
     */
    public String stPlaceName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stPlaceName);
    }

    /**
     * 办理点name
     */
    public void setStPlaceName(String stPlaceName) {
        stPlaceName = StringUtil.substringBySize(stPlaceName, 50, "GB18030");
        this.stPlaceName = stPlaceName;
    }
    
	/**
     * 预约天数
     */
    public BigDecimal getStReservationDay() {
        return this.stReservationDay;
    }
    
    /**
     * 预约天数
     */
    public String stReservationDay2Html(int precision) {
        if (this.stReservationDay == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.stReservationDay);
        }
    }

    /**
     * 预约天数
     */
    public void setStReservationDay(BigDecimal stReservationDay) {
        this.stReservationDay = stReservationDay;
    }

	/**
     * 预约提前天数
     */
    public BigDecimal getStReservationEarlyDay() {
        return this.stReservationEarlyDay;
    }
    
    /**
     * 预约提前天数
     */
    public String stReservationEarlyDay2Html(int precision) {
        if (this.stReservationEarlyDay == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.stReservationEarlyDay);
        }
    }

    /**
     * 预约提前天数
     */
    public void setStReservationEarlyDay(BigDecimal stReservationEarlyDay) {
        this.stReservationEarlyDay = stReservationEarlyDay;
    }

	/**
     * 星期一是否能预约
     */
    public BigDecimal getNmMonday() {
        return this.nmMonday;
    }
    
    /**
     * 星期一是否能预约
     */
    public String nmMonday2Html(int precision) {
        if (this.nmMonday == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmMonday);
        }
    }

    /**
     * 星期一是否能预约
     */
    public void setNmMonday(BigDecimal nmMonday) {
        this.nmMonday = nmMonday;
    }

	/**
     * 星期二是否能预约
     */
    public BigDecimal getNmTuesday() {
        return this.nmTuesday;
    }
    
    /**
     * 星期二是否能预约
     */
    public String nmTuesday2Html(int precision) {
        if (this.nmTuesday == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmTuesday);
        }
    }

    /**
     * 星期二是否能预约
     */
    public void setNmTuesday(BigDecimal nmTuesday) {
        this.nmTuesday = nmTuesday;
    }

	/**
     * 星期三是否能预约
     */
    public BigDecimal getNmWednesday() {
        return this.nmWednesday;
    }
    
    /**
     * 星期三是否能预约
     */
    public String nmWednesday2Html(int precision) {
        if (this.nmWednesday == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmWednesday);
        }
    }

    /**
     * 星期三是否能预约
     */
    public void setNmWednesday(BigDecimal nmWednesday) {
        this.nmWednesday = nmWednesday;
    }

	/**
     * 星期四是否能预约
     */
    public BigDecimal getNmThursday() {
        return this.nmThursday;
    }
    
    /**
     * 星期四是否能预约
     */
    public String nmThursday2Html(int precision) {
        if (this.nmThursday == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmThursday);
        }
    }

    /**
     * 星期四是否能预约
     */
    public void setNmThursday(BigDecimal nmThursday) {
        this.nmThursday = nmThursday;
    }

	/**
     * 星期五是否能预约
     */
    public BigDecimal getNmFriday() {
        return this.nmFriday;
    }
    
    /**
     * 星期五是否能预约
     */
    public String nmFriday2Html(int precision) {
        if (this.nmFriday == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmFriday);
        }
    }

    /**
     * 星期五是否能预约
     */
    public void setNmFriday(BigDecimal nmFriday) {
        this.nmFriday = nmFriday;
    }

	/**
     * 星期六是否能预约
     */
    public BigDecimal getNmSaturday() {
        return this.nmSaturday;
    }
    
    /**
     * 星期六是否能预约
     */
    public String nmSaturday2Html(int precision) {
        if (this.nmSaturday == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmSaturday);
        }
    }

    /**
     * 星期六是否能预约
     */
    public void setNmSaturday(BigDecimal nmSaturday) {
        this.nmSaturday = nmSaturday;
    }

	/**
     * 星期日是否能预约
     */
    public BigDecimal getNmSunday() {
        return this.nmSunday;
    }
    
    /**
     * 星期日是否能预约
     */
    public String nmSunday2Html(int precision) {
        if (this.nmSunday == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmSunday);
        }
    }

    /**
     * 星期日是否能预约
     */
    public void setNmSunday(BigDecimal nmSunday) {
        this.nmSunday = nmSunday;
    }

	/**
     * 优先规则
     */
    public BigDecimal getNmIsPriority() {
        return this.nmIsPriority;
    }
    
    /**
     * 优先规则
     */
    public String nmIsPriority2Html(int precision) {
        if (this.nmIsPriority == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmIsPriority);
        }
    }

    /**
     * 优先规则
     */
    public void setNmIsPriority(BigDecimal nmIsPriority) {
        this.nmIsPriority = nmIsPriority;
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
     * 部门ID
     */
    public BigDecimal getNmOrganNodeId() {
        return this.nmOrganNodeId;
    }
    
    /**
     * 部门ID
     */
    public String nmOrganNodeId2Html(int precision) {
        if (this.nmOrganNodeId == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmOrganNodeId);
        }
    }

    /**
     * 部门ID
     */
    public void setNmOrganNodeId(BigDecimal nmOrganNodeId) {
        this.nmOrganNodeId = nmOrganNodeId;
    }
    
	/**
     * 扩展字段1
     */
    public String getStExt1() {
        return this.stExt1;
    }
    
    /**
     * 扩展字段1
     */
    public String stExt12Html() {
        return StringHelper.replaceHTMLSymbol(this.stExt1);
    }

    /**
     * 扩展字段1
     */
    public void setStExt1(String stExt1) {
        stExt1 = StringUtil.substringBySize(stExt1, 50, "GB18030");
        this.stExt1 = stExt1;
    }
    
	/**
     * 扩展字段2
     */
    public String getStExt2() {
        return this.stExt2;
    }
    
    /**
     * 扩展字段2
     */
    public String stExt22Html() {
        return StringHelper.replaceHTMLSymbol(this.stExt2);
    }

    /**
     * 扩展字段2
     */
    public void setStExt2(String stExt2) {
        stExt2 = StringUtil.substringBySize(stExt2, 50, "GB18030");
        this.stExt2 = stExt2;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}