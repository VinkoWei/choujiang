package com.vinko.choujiang.service;

import com.vinko.choujiang.domain.Goods;
import com.vinko.choujiang.alias.AliasMethod;
import com.vinko.choujiang.dao.GoodsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
/**
 * @Author:Vinko
 * @Description:
 * @Date: Created in 12:13 2018-06-03
 * @Modified By:
 */
@Service
public class GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    //抽奖的对象
    public static AliasMethod aliasMethod;

    //初始化抽象对象
    public void init() {
        int n = 4;  //奖品数
        double[] event = new double[n];
        //设置中奖概率
        event[0] = 0.01;  // 1等奖
        event[1] = 0.01;	// 2等奖
        event[2] = 0.01;    // 3等奖
        event[3] = 0.97;  // 未中奖
        aliasMethod = new AliasMethod(event, n);
    }


    @Transactional
    public int draw(){
        init();
        //获取抽奖结果
        int result  = aliasMethod.next();
        //进行数据库操作
        Goods goods = goodsDao.findById((long)(result + 1)).get();
        goods.setNumber(goods.getNumber() - 1);
        goodsDao.saveAndFlush(goods);
        return result;
    }

}
