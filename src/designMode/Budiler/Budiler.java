package designMode.Budiler;

import java.util.ArrayList;
import java.util.List;

import designMode.Factory.EmailSender;
import designMode.Factory.Sender;
import designMode.Factory.SmsSender;

public class Budiler {
	
	private List<Sender> senderList=new ArrayList<Sender>();
	
	public void  produceEmailSender(int num){
		for(int i=0;i<num;i++){
			senderList.add(new EmailSender());
		}
	}
	
	public void produceSmsSender(int num){
		for(int i=0;i<num;i++){
			senderList.add(new SmsSender());
		}
	}
	
	
	public List<Sender> getSenderList() {
		return senderList;
	}

	public void setSenderList(List<Sender> senderList) {
		this.senderList = senderList;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Budiler budiler=new Budiler();
		budiler.produceEmailSender(10);
		budiler.produceSmsSender(5);
		List<Sender> list=budiler.getSenderList();
		for(Sender s:list){
			s.send();
		}

	}

}
