package com.test.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName:MemberCouponsInfoVO
 * @Description:会员优惠券信息VO
 * @Author:lm.sun
 * @Date:2019/9/24 14:21
 */
@ToString
@Getter
@Setter
public class MemberCouponsInfoVO {

    /**
     * 优惠券批次编码
     */
    private String couponsBatchNo;

    /**
     * 优惠券编码
     */
    private String couponsNo;

    /**
     * 优惠券名称
     */
    private String couponsName;

    /**
     * 优惠券类型1只要是大于优惠券的金额 2 必须满足多少金额才能优惠
     */
    private String couponsType;

    /**
     * 优惠券满多少金额才能使用
     */
    private BigDecimal limitAmount;

    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;

    /**
     * 优惠券商品使用限制,商品编码用逗号分隔,如果没有则说明全品类优惠券
     */
    private String productsLimit;

    /**
     * 优惠券会员等级使用限制,会员等级用逗号分隔,如果为空,则说明所有会员都能用
     */
    private String memberLevelLimit;

    /**
     * 优惠券开始时间
     */
    private Date startTime;

    /**
     * 优惠券结束时间
     */
    private Date endTime;

    /**
     * 会员编码
     */
    private String memberNo;

    /**
     * 用户优惠券状态 1 可用 2 已使用 3已过期
     */
    private String status;

    /**
     * 用户获得优惠券时间
     */
    private Date obtainTime;

}
