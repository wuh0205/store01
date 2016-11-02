package com.wuh.TinkInJavaTest.Operators;

/**
 * 直接常量
 *  1.十六进制以前缀0x开头,后面可接数字0—9或a—f
 *  2.八进制以前缀0开头,后面跟数字0~7
 *  3.直接常量表示中字母不区分大小写
 *  4.直接常量后缀表示类型,l或L表示long(一般使用L容易区分),f或F表示float,d或D表示double
 */
public class Literals {
    public static void main(String[] args){
        //十六进制以前缀0x开头,后面可接数字0—9或a—f
        int i1=0x2f;//字母大小写均可 0x2f=0X2F
        System.out.println(Integer.toBinaryString(i1));//可用Integer.toBinaryString输出2进制

        //八进制以前缀0开头,后面跟数字0~7
        int i3=0177;
        System.out.println(Integer.toBinaryString(i3));

        char c=0xffff;//max char hex value
        System.out.println(Integer.toBinaryString(c));

        byte b=0x7f;//max byte hex value
        System.out.println(Integer.toBinaryString(b));

        short s=0x7fff;//max shoat hex value
        System.out.println(Integer.toBinaryString(s));

        //long suffix
        long n1=200L;
        long n2=200l;
        long n3=200;
        System.out.println(n1+" | "+n2+" | "+n3);

        //float suffix
        float f1=1F;
        float f2=1f;
        float f3=1;
        System.out.println(f1+" | "+f2+" | "+f3);

        //double suffix
        double d1=2D;
        double d2=2d;
        double d3=2;
        System.out.println(d1+" | "+d2+" | "+d3);
    }
}
/*
Output:
101111
1111111
1111111111111111
1111111
111111111111111
200 | 200 | 200
1.0 | 1.0 | 1.0
2.0 | 2.0 | 2.0
*/
