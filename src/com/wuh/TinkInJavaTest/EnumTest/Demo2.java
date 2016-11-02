package com.wuh.TinkInJavaTest.EnumTest;

enum Car{
    BUS,SEDAN,TRUCK
}
/**
 *  打印一个实例的时候回自动调用toString()方法
 */
public class Demo2 {
    Car car;
    Demo2(Car car){
        this.car=car;
    }

    @Override
    public String toString() {
        return "Demo2:"+this.car;
    }

    public static void main(String[] args){
        System.out.println(new Demo2(Car.BUS));//打印一个实例的时候回自动调用toString()方法
        System.out.println(new Demo2(Car.SEDAN));
        System.out.println(new Demo2(Car.TRUCK));
    }
}


/**
 * 枚举描述
 *  1.枚举中可以声明变量和方法
 *  2.如果有枚举的描述,该枚举中必须有构造方法
 *  3.枚举的构造方法只能在内部使用,所以是否加修饰关键词private等都是一样的,一旦
 *  enum定义结束,编译器就不允许再使用构造器来创建实例了
 */
enum Language{
    JAVA("object language,always use in server."),
    SWIFT("It is new language from Apple company."),
    PYTHON("Always use in data statistics.");

    private String description;

    Language(String description) {//如果有枚举的描述,该枚举中必须有构造方法
        this.description=description;
    }

    public String getDescription() {
        return description;
    }

    public static void main(String[] args){
        for (Language lng : Language.values()){
            System.out.println(lng+": "+lng.getDescription());
        }
    }
}


enum Signal{
    RED,GREEN,YELLOW
}

/**
 * enum在switch语句中用法
 *   1.在case中不能用Signal.RED
 */
class TrafficLight{
    private static Signal signal=Signal.GREEN;
    public static void change(){
        switch (signal){
            case RED://在case中不能用Signal.RED
                signal=Signal.GREEN;
                break;
            case GREEN:
                signal=Signal.YELLOW;
                break;
            case YELLOW:
                signal=Signal.RED;
                break;
        }
    }

    @Override
    public String toString() {
        return "TrafficLight : "+signal;
    }

    public static void main(String[] args){
        TrafficLight t=new TrafficLight();
        for(int i=0;i<7;i++){
            t.change();
            System.out.println(t);
        }
    }
}

