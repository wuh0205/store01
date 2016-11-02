package com.wuh.TinkInJavaTest.initAndClean;

/**
 * 当需要返回当前对象引用时,就在return中这样写
 */
public class Leaf {
    private int i=0;

    private Leaf increment(){
        i++;
        return this;
    }

    public int getI() {
        return i;
    }

    public static void main(String[] args){
        Leaf leaf=new Leaf();
        System.out.println(leaf.increment().increment().increment().getI());//3
    }
}
