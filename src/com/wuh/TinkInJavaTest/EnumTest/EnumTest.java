package com.wuh.TinkInJavaTest.EnumTest;

enum Animal {
    DOG,CAT,WOLF
}

/**
 *  1.输出声明时的顺序:  a.ordinal()
 *  2.比较enum实例是否相同:
 *    a.compareTo(Animal.CAT)
 *    a.equals(Animal.CAT)
 *    a == Animal.CAT
 *  3.获取enum实例所属类的名字:  a.getDeclaringClass()
 *  4.返回enum实例声明时的名字:  a.name()
 *  5.通过String类型的enum实例名字,返回一个enum实例: Animal animal=Enum.valueOf(Animal.class,str);
 */
public class EnumTest {

    public static void main(String[] args){
        for (Animal a : Animal.values()){
            System.out.println(a+" ordinal:"+a.ordinal());//输出声明时的顺序
            //比较enum实例
            System.out.print(a.compareTo(Animal.CAT)+" ");
            System.out.print(a.equals(Animal.CAT)+" ");
            System.out.println(a == Animal.CAT);

            System.out.println(a.getDeclaringClass());//获取enum实例所属类的名字
            System.out.println(a.name());//返回enum实例声明时的名字
            System.out.println("--------------------------------");//
        }

        //produce an enum value from a string name
        for (String str : "DOG CAT WOLF".split(" ")){
            Animal animal=Enum.valueOf(Animal.class,str);
            System.out.println(animal);
        }
    }
}
/*Output:

DOG ordinal:0
-1 false false
class com.wuh.TinkInJavaTest.EnumTest.Animal
DOG
--------------------------------
CAT ordinal:1
0 true true
class com.wuh.TinkInJavaTest.EnumTest.Animal
CAT
--------------------------------
WOLF ordinal:2
1 false false
class com.wuh.TinkInJavaTest.EnumTest.Animal
WOLF
--------------------------------
DOG
CAT
WOLF
 */