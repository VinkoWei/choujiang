package com.vinko.choujiang.domain;
import javax.persistence.*;
/**
 * @Author:Vinko
 * @Description:
 * @Date: Created in 11:44 2018-06-03
 * @Modified By:
 */
@Entity
@Table(name = "tb_goods")
public class Goods {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "number")
    private int number;
    @Column(name = "name")
    private int name;

    public Goods() {
    }

    public Goods(Long id, int number, int name) {
        this.id = id;
        this.number = number;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", number=" + number +
                ", name=" + name +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}
