package com.test.bean.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Description 兑换券外码发放
 * @Author  Hunter
 * @Date 2019-08-07
 */

@Setter
@Getter
@Builder
public class CouponGrantVO  {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 兑换券编号
     */
    private String couponNo;

    /**
     * 兑换券外码
     */
    private String outerCode;

    /**
     * 兑换券外码卡密
     */
    private String outerCodePass;

    /**
     * 发放人手机号
     */
    private String phone;

    /**
     * 伯乔订单编号
     */
    private String bcTradeNo;

    /**
     * 商品编号
     */
    private String goodsNo;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 外码有效起始日期
     */
    private String startDate;

    /**
     * 外码有效结束日期
     */
    private String endDate;

    /**
     * 取码订单状态 0.失败 1.成功
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
