package designMode.Proxy;

public class Proxy implements Sourceable{
	
	private Source source;
	
	public Proxy(){
		this.source=new Source();
	}


	@Override
	public void method1() {
		// TODO Auto-generated method stub
		before();
		source.method1();
		after();
	}
	
	public void before(){
		 System.out.println("after proxy!");  
	}
	
	public void after(){
		System.out.println("before proxy!");  
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Proxy pr=new Proxy();
		pr.method1();

	}

}
