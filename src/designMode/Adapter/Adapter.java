package designMode.Adapter;

/**
 * 类适配器
 * @author wuh
 *
 */
public class Adapter extends Source implements Targetable{
	@Override
	public void method1() {
		// TODO Auto-generated method stub
		System.out.println("this Targetable method1");
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Targetable tga=new Adapter();
		tga.method1();
		tga.method2();

	}

}
