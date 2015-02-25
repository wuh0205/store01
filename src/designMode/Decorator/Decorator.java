package designMode.Decorator;

public class Decorator implements Sourceable{
	private Source source;
	
	public Decorator(Source source){
		this.source=source;
	}

	@Override
	public void method1() {
		// TODO Auto-generated method stub
		System.out.println("source method1 before..");
		source.method1();
		System.out.println("source method1 after..");
	}

	@Override
	public void method2() {
		// TODO Auto-generated method stub
		System.out.println("source method2 before..");
		source.method2();
		System.out.println("source method2 after..");
	}

}
