package com.test.bean.vo;

import lombok.Data;

/**
 * @Title: CouponsDataVO.java
 * @Package com.bc.code.transform.api.model.vo
 * @Description: 风火轮取码返回串码数据
 * @author lm.sun
 * @date 2020/5/26 11:34
 */
@Data
public class CouponsDataVO {
    /** 卡号 **/
    private String code;
    /** 卡密 **/
    private String codePass;
    /** 开始日期 **/
    private String startDate;
    /** 结束日期 **/
    private String endDate;

}
