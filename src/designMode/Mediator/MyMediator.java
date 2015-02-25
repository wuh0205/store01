package designMode.Mediator;

public class MyMediator implements Mediator{
	private User1 user1;
	private User2 user2;

	@Override
	public void createMediator() {
		// TODO Auto-generated method stub
		user1=new User1(this);
		user2=new User2(this);
	}

	@Override
	public void workAll() {
		// TODO Auto-generated method stub
		user1.work();
		user2.work();
	}
	
	public static void main(String[] args){
		Mediator mediator=new MyMediator();
		mediator.createMediator();
		mediator.workAll();
	}

}
