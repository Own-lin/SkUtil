package com.sktuil.spring.spring;


import com.sktuil.spring.spring.unit.OpStandardData;

/**
 * 待记录操作的标准数据
 * @author zhan yan
 * @date 2022/11/16
 */
public interface OpDataInterface<PARAM> {

    /**
     * Convert target
     * @param target source obj. If coder want to DIY a result class, please extend {@link OpStandardData}
     * @return which class extend {@link OpStandardData}
     */
    <T extends OpStandardData> T convert(PARAM target);

}
