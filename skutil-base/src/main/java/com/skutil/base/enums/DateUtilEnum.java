package com.skutil.base.enums;

/**
 * @author zhan yan
 * @date 2022/11/18
 */
public enum DateUtilEnum {

    /**
     *
     */
    Y2S("yyyy-MM-dd HH:mm:ss"),

    /**
     *
     */
    Y2D("yyyy-MM-dd"),

    /**
     *
     */
    M2D("MM-dd");

    private final String type;

    DateUtilEnum(String type){
        this.type = type;
    }

    public String get(){
        return this.type;
    }

}
