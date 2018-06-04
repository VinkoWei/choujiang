package com.vinko.choujiang;

import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    GoodsService goodsService;

    @RequestMapping("/draw")
    public int draw() {
        synchronized (this){
            int result = goodsService.draw();
            return result;
        }
    }


}
