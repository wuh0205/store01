package designMode.Bridge;

public abstract class Bridge {
	
	private Sourceable sourcealbe;
	
	public void method(){
		sourcealbe.method();
	}

	public Sourceable getSourcealbe() {
		return sourcealbe;
	}

	public void setSourcealbe(Sourceable sourcealbe) {
		this.sourcealbe = sourcealbe;
	}
	
}
