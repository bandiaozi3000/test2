package com.test.bean;

import java.math.BigDecimal;

/**
* @Title: ElemeReportDto
* @Package com.bc.eleme.platform.dto
* @Description:   业务形态报表信息查询
* @author 刘彦龙
* @date 2020/9/9 14:27 
* @version V1.0   
*/
public class ElemeReportLayoutDto {
    /** 业务形态*/
    private String layoutDesc;

    /** 资源位编号*/
    private String activityNo;
    /** 资源位名称*/
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

    public String getLayoutDesc() {
        return layoutDesc;
    }

    public void setLayoutDesc(String layoutDesc) {
        this.layoutDesc = layoutDesc;
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

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
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
}
