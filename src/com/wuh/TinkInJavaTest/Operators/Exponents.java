package com.wuh.TinkInJavaTest.Operators;

/**
 * 指数计数法
 *  1.e代表10的幂次
 *  2.编译器通常将指数作为双精度处理,如果去掉后缀f会报错,提示需要转成double
 *  3.如果编译器可以识别类型,无需加后缀
 */
public class Exponents {
    public static void main(String[] args){
        float expFloat=1.39e-43f;//字符不区分大小写,1.39e-43f=1.39E-43F, 去掉后缀f会报错,提示需要转成double
        System.out.println(expFloat);//1.39E-43

        double expDouble=47e47d;
        double expDOuble2=47e47;
        System.out.println(expDouble);//4.7E48

    }
}
