package designMode.Factory;

public class EmailSenderFactory implements SenderFactory{

	@Override
	public Sender getSender() {
		// TODO Auto-generated method stub
		return new EmailSender();
	}

}
