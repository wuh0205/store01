package designMode.Observer;

public class MySubject extends AbstractSubject{

	@Override
	public void operation() {
		// TODO Auto-generated method stub
		System.out.println("update self!");
		notifyObservers();
	}
	
	public static void main(String[] args){
		Observer observer1=new Observer1();
		Observer observer2=new Observer2();
		
		Subject subject=new MySubject();
		subject.add(observer1);
		subject.add(observer2);
		subject.operation();
		
	}

}
