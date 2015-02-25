package designMode.Bridge;

public class MyBridge extends Bridge{
	
	public void method(){
		super.getSourcealbe().method();
	}
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bridge bridge=new MyBridge();
		Sourceable s1=new SourceSub1();
		Sourceable s2=new SourceSub2();
		
		bridge.setSourcealbe(s1);
		bridge.method();
		bridge.setSourcealbe(s2);
		bridge.method();
	}

}
