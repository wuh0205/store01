package designMode.Adapter;

/**
 * 接口适配器
 * @author wuh
 *
 */
public abstract class Wrapper2 implements Targetable{
	
	public void method1(){};
	
	public void method2(){};
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Targetable tab1=new SourceSub1();
		Targetable tab2=new SourceSub2();
		tab1.method1();
		tab1.method2();
		tab2.method1();
		tab2.method2();

	}

}
