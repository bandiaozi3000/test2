
package com.test.exception;

public enum ApiCallResult {
    SUCCESS("000000", "操作成功"),
    SWEEPSTAKE_FAIL("001000", "抽奖失败,请稍后重试!"),
    SWEEPSTAKE_NOT("001001", "很遗憾,本次未中奖!"),
    ACTIVITY_NOT_BEGIN("002000", "活动未开始!"),
    ACTIVITY_FINISHED("002001", "活动已结束!"),
    USEER_SWEEPSTAKE_NUM_ZERO("003000", "抽奖次数已用完!"),
    ALREADY_SIGN_IN("004000", "您今天已经签到了"),
    NO_PRODUCT("004001", "签到领奖今日已用完"),
    RECEIVEAWARD_FAIL("005000", "领取奖品失败,请稍后重试"),
    AWARD_ALREADY("005001", "奖品已领取"),
    AWARD_PAST("005002", "奖品已过期"),
    NO_AWARD("005003", "没有可以领取的奖品"),
    EMPTY_ARGUMENT("000001", "参数缺失"),
    ILLEGAL_ARGUMENT("000002", "参数异常"),
    SIGNERROR("000003", "签名错误"),
    UNSUPPORTED("000004", "格式不支持"),
    DBERROR("000005", "持久化异常"),
    FAILURE("999999", "操作失败"),
    EXCEPTION("100001", "系统异常");

    private String code;
    private String desc;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private ApiCallResult(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
