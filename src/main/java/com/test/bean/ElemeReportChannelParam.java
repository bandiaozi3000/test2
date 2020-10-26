package com.test.bean;

import lombok.Data;

/**
 * @author 刘彦龙
 * @version V1.0
 * @Title: ElemeReportDto
 * @Package com.bc.eleme.platform.dto
 * @Description: 报表对应日期信息查询
 * @date 2020/9/9 14:27
 */
@Data
public class ElemeReportChannelParam {


    /**
     * 年份
     */
    private String year;

    /**
     * 开始时间 yyyy-mm-dd
     */
    private String startTime;
    /**
     * 结束时间  yyyy-mm-dd
     */
    private String endTime;

}