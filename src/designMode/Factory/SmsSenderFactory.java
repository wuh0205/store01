package designMode.Factory;

public class SmsSenderFactory implements SenderFactory{

	@Override
	public Sender getSender() {
		// TODO Auto-generated method stub
		return new SmsSender();
	}

}
