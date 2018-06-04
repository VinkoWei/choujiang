package com.vinko.choujiang.alias;
import java.util.*;
/**
 * @Author:Vinko
 * @Description:
 * @Date: Created in 0:23 2018-05-31
 * @Modified By:
 */
public class AliasMethod {

    private Random rand;
    private double[] event;
    private int[] alias; // 存储另外事件的标号
    private double[] probs; // 存储事件的概率

    private Stack<Point> small; // 概率小于1
    private Stack<Point> large; // 概率大于1
    private int n; // 事件个数

    private class Point {
        int index;
        double prob;

        public Point(int index, double prob) {
            this.index = index;
            this.prob = prob;
        }
    }

    public AliasMethod(double[] event, int n) {
        this.event = event;
        this.n = n;
        // 初始化
        alias = new int[n];
        probs = new double[n];
        small = new Stack<Point>();
        large = new Stack<Point>();
        // 初始化rand 通过一个种子(按照美团取种子的方法，开奖日收盘时的上证指数 × 收盘时的深证成指 × 10000 =
        // 12位数。（指数以证交所公布数字为准）)
        // 这里就直接取当天时间
        rand = new Random(System.currentTimeMillis());
        init();
    }

    /*
     * Initialization
     */
    public void init() {

        int i;
        // event中每个事件的概率乘以n
        for (i = 0; i < n; i++) {
            event[i] *= n;
        }

        // 初始化small和large堆栈
        // 对所有事件的概率缩放后进行分类
        for (i = 0; i < n; i++) {
            if (event[i] >= 1) {
                // 入栈
                large.push(new Point(i, event[i]));
            } else if (event[i] < 1 && event[i] > 0) {
                small.push(new Point(i, event[i]));
            }
        }

        // 构建prob/alias数组
        while (!small.empty() && !large.empty()) {
            // 弹出small/large栈顶
            Point l = small.pop();
            Point g = large.pop();
            // 赋值
            probs[l.index] = l.prob;
            alias[l.index] = g.index;
            // 更新g的prob(概率)
            g.prob = (g.prob + l.prob) - 1;

            if (g.prob < 1) {
                small.push(g);
            } else if (g.prob >= 1) {
                large.add(g);
            }
        }
        while (!large.empty()) {
            Point g = large.pop();
            probs[g.index] = 1.0;
        }
        while (!small.empty()) {
            Point l = small.pop();
            probs[l.index] = 1.0;
        }

    }
    //返回结果
    public int next() {
        int column = rand.nextInt(n);
        boolean coinToss = rand.nextDouble() < probs[column];
        return coinToss ? column : alias[column];
    }
}
