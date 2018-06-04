package com.vinko.choujiang.dao;

import com.vinko.choujiang.domain.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
/**
 * @Author:Vinko
 * @Description:
 * @Date: Created in 12:04 2018-06-03
 * @Modified By:
 */
public interface BasicRepository extends CrudRepository<Goods,Long>,JpaRepository<Goods,Long> {

}
