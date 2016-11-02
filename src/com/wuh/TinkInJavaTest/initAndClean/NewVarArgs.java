package com.wuh.TinkInJavaTest.initAndClean;

/**
 * Created by wuh on 16/8/20.
 */
public class NewVarArgs {
    static void printArray(Object... args){
        for(Object o : args)
            System.out.print(o+" ");
        System.out.println();
    }

    public static void main(String[] args){
        printArray(new Integer(47),new Float(34.2),new Double(34.333));
        printArray("a","b","c","d");
        printArray(new Integer[]{1,2,3,4,5});

    }
}
