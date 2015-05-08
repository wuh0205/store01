package designMode.Singleto;

public class Singleto {
	private static volatile Singleto instance;
	
	private Singleto(){};//被private修饰后的构造方法不能除本类其他地方被创建实例，也就是不能被new
	
	public static Singleto getInstance(){
		if(instance==null){
			synchronized(Singleto.class){
				if(instance==null){
					instance=new Singleto();
				}
			}
		}
		return instance;
	}
	
	public void sayHello(){
		System.out.println("Hello~");
	}
	
}
