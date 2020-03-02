package com.test.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class DateTest {

    @JSONField(format = "yyyy-MM-dd ")
    private Date date1 = new Date();

    @JsonFormat(pattern = "yyyy-MM-dd ",timezone = "GMT+8")
    private Date date2 = new Date();

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public Date getDate1() {

        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public DateTest(Date date1, Date date2) {
        this.date1 = date1;
        this.date2 = date2;
    }
    public DateTest() {
    }

    @Override
    public String toString() {
        return "DateTest{" +
                "date1=" + date1 +
                ", date2=" + date2 +
                '}';
    }
}
