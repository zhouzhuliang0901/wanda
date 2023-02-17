package com.wondersgroup.common.factory;


public interface IEnum<K, V> {

    /**
     * Value
     * 
     * @return K
     */
    K getValue();

    /**
     * 名称
     * 
     * @return V
     */
    V getName();

}
