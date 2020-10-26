package com.test.util;

import com.test.bean.WchatLotteryDomain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * wchat大转盘抽奖活动
 *
 * @author yanst 2016/4/23 9:23
 */
public class BigWheelDrawUtil {


    /**
     * 给转盘的每个角度赋初始值
     *
     * @return
     */
    private final static List<WchatLotteryDomain> initDrawList = new ArrayList<WchatLotteryDomain>() {{
        add(new WchatLotteryDomain(1, "200", 1));
        add(new WchatLotteryDomain(2, "100", 3));
        add(new WchatLotteryDomain(3, "50", 30));
        add(new WchatLotteryDomain(4, "30", 30));
        add(new WchatLotteryDomain(5, "20", 26));
        add(new WchatLotteryDomain(6, "10", 10));
    }};

    /**
     * 生成奖项
     * @return
     */
    public static WchatLotteryDomain generateAward() {
        List<WchatLotteryDomain> initData = initDrawList;
        long result = randomnum(1, 100);
        int line = 0;
        int temp = 0;
        WchatLotteryDomain returnobj = null;
        int index = 0;
        for (int i = 0; i < initDrawList.size(); i++) {
            WchatLotteryDomain obj2 = initDrawList.get(i);
            int c = obj2.getV();
            temp = temp + c;
            line = 100 - temp;
            if (c != 0) {
                if (result > line && result <= (line + c)) {
                    returnobj = obj2;
                    break;
                }
            }
        }
        return returnobj;
    }

    // 获取2个值之间的随机数
    private static long randomnum(int smin, int smax) {
        int range = smax - smin;
        double rand = Math.random();
        return (smin + Math.round(rand * range));
    }


    public static void main(String[] args) {
        Map<Integer,Integer> hashMap = new HashMap<>();
//        hashMap.put(1,0);
//        hashMap.put(2,0);
//        hashMap.put(3,0);
//        hashMap.put(4,0);
//        hashMap.put(5,0);
//        hashMap.put(6,0);
        for(int i = 0;i<10000;i++){
            System.out.println(i);
            WchatLotteryDomain wchatLotteryDomain =generateAward();
            Integer count = hashMap.get(wchatLotteryDomain.getId());
            hashMap.put(wchatLotteryDomain.getId(),count+1);
        }
        System.out.println(hashMap);
    }

}
