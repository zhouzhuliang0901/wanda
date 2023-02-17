package com.wondersgroup.dataitem.item276652591922.bean;

import java.io.Serializable;

import org.jeecgframework.poi.excel.annotation.Excel;

@SuppressWarnings("serial")
public class FoodDictionary implements Serializable{
    @Excel(name = "Key")
    private String key;

    @Excel(name = "Value")
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
