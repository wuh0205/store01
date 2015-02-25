package designMode.Adapter;

/**
 * 对象适配器
 * @author wuh
 *
 */
public class Wrapper implements Targetable{
	Source source;
	
	public Wrapper(Source source){
		this.source=source;
	}

	@Override
	public void method1() {
		// TODO Auto-generated method stub
		System.out.println("this Targetable method1 from Wrapper");
	}

	@Override
	public void method2() {
		// TODO Auto-generated method stub
		System.out.println("...............");
		source.method2();
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Targetable tab=new Wrapper(new Source());
		tab.method1();
		tab.method2();

	}

}
