package com.vinko.choujiang;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:Vinko
 * @Description:
 * @Date: Created in 17:33 2018-05-29
 * @Modified By:
 */
@RestController
public class Controller {

    //抽奖对象
    public static AliasMethod aliasMethod;

    //初始化抽象对象
    public void init() {
        int n = 4;  //奖品数
        double[] event = new double[n];
        //设置中奖概率
        event[0] = 0.1;  // 1等奖
        event[1] = 0.1;	// 2等奖
        event[2] = 0.1;    // 3等奖
        event[3] = 0.7;  // 未中奖
        aliasMethod = new AliasMethod(event, n);
    }

    @RequestMapping("/draw")
    public int draw(){
        init();
        int result  = aliasMethod.next();
        System.out.println(result);
        return result;
    }

}
