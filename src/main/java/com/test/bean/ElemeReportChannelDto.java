package com.test.bean;

import java.math.BigDecimal;

/**
* @Title: ElemeReportDto
* @Package com.bc.eleme.platform.dto
* @Description:   渠道报表信息查询
* @author 刘彦龙
* @date 2020/9/9 14:27 
* @version V1.0   
*/
public class ElemeReportChannelDto {
    /** 渠道id*/
    private String channelId;
    /** 渠道名称*/
    private String channelName;
    /** 活动编号*/
    private String activityNo;
    /** 活动名称*/
    private String activityName;
    /** 订单数*/
    private Integer orderCount;
    /** 交易金额*/
    private BigDecimal salesAmount;
    /** 税前毛利*/
    private BigDecimal preTaxAmount;
    /** 税后毛利*/
    private BigDecimal afterTaxAmount;

    /** 现金金额*/
    private BigDecimal cashAmount;

    /** 活动补贴金额*/
    private BigDecimal discountAmount;

    /** 积分补贴金额*/
    private BigDecimal pointAmount;

    public BigDecimal getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(BigDecimal cashAmount) {
        this.cashAmount = cashAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getPointAmount() {
        return pointAmount;
    }

    public void setPointAmount(BigDecimal pointAmount) {
        this.pointAmount = pointAmount;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getActivityNo() {
        return activityNo;
    }

    public void setActivityNo(String activityNo) {
        this.activityNo = activityNo;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public BigDecimal getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(BigDecimal salesAmount) {
        this.salesAmount = salesAmount;
    }

    public BigDecimal getPreTaxAmount() {
        return preTaxAmount;
    }

    public void setPreTaxAmount(BigDecimal preTaxAmount) {
        this.preTaxAmount = preTaxAmount;
    }

    public BigDecimal getAfterTaxAmount() {
        return afterTaxAmount;
    }

    public void setAfterTaxAmount(BigDecimal afterTaxAmount) {
        this.afterTaxAmount = afterTaxAmount;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }


}
