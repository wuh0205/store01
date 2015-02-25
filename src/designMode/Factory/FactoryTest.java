package designMode.Factory;

public class FactoryTest {
	
	public Sender getSender(String type){
		if("email".equals(type)){
			return new EmailSender();
		}else if("sms".equals(type)){
			return new SmsSender();
		}else{
			System.out.println("请输入正确的类型!");
		}
		return null;
	}
	
	public static Sender produceMail(){  
        return new EmailSender();  
    }  
      
    public static Sender produceSms(){  
        return new SmsSender();  
    }  

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//普通工厂模式
		FactoryTest ft=new FactoryTest();
		ft.getSender("email").send();
		ft.getSender("sms").send();
		//静态工厂模式
		FactoryTest.produceMail().send();
		FactoryTest.produceSms().send();
		//抽象工厂模式
		SenderFactory sfy1=new EmailSenderFactory();
		SenderFactory sfy2=new SmsSenderFactory();
		sfy1.getSender().send();
		sfy2.getSender().send();

	}

}
