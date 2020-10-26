package com.test.bean.vo;


import lombok.Data;

/**
 * @Title: FetchCouponVO.java
 * @Package com.bc.code.transform.api.model.vo
 * @Description: 风火轮取码返回数据
 * @author lm.sun
 * @date 2020/5/26 11:39
 */
@Data
public class FetchCouponVO {
    /**  核销渠道订单号 **/
    private String verifyOutTradeNo;
    /**  核销伯乔订单号 **/
    private String verifyBcTradeNo;
    /**  取码伯乔订单号 **/
    private String orderBcOrderNo;
    /**  活动名称 **/
    private String activityName;
    /**  商品名称 **/
    private String productName;
    /**  商品编号 **/
    private String productNo;
    /**  内码编号 **/
    private String bcActivityProductNo;
    /**  外码编号 **/
    private String outActivityProductNo;
    /**  券码信息VO **/
    private CouponsDataVO coupon;

}
