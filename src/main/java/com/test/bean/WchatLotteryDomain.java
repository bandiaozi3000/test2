package com.test.bean;

import java.io.Serializable;

/**
 * 微信用户分享中奖基础数据类
 *
 * @author yanst // :
 */
public class WchatLotteryDomain implements Serializable {


    private Integer id;

    //中奖金额
    private String prize;

    //中奖率
    private Integer v;

    public WchatLotteryDomain(Integer id, String prize, Integer v) {
        this.id = id;
        this.prize = prize;
        this.v = v;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return "WchatLotteryDomain{" +
                "id=" + id +
                ", prize='" + prize + '\'' +
                ", v=" + v +
                '}';
    }
}