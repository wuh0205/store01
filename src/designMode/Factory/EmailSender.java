package designMode.Factory;

public class EmailSender implements Sender{

	@Override
	public void send() {
		// TODO Auto-generated method stub
		System.out.println("This is email send!");
	}

}
