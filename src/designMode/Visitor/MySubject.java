package designMode.Visitor;

public class MySubject implements Subject{

	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub
		visitor.visit(this);
	}

	@Override
	public String getNumber() {
		// TODO Auto-generated method stub
		return "1001";
	}
	
	
	public static void main(String[] args){
		Visitor vistior=new MyVisitor();
		Subject subject=new MySubject();
		subject.accept(vistior);
	}

}
