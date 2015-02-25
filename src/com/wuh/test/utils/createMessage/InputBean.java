package com.wuh.test.utils.createMessage;

public class InputBean {

	private String messageName;

	private String tid;

	private String num;

	private String clientID;
	
	private String instID;
	
	private String memberID;
	
	private String buyOrSell;


	public String getMessageName() {
		return messageName;
	}

	public void setMessageName(String messageName) {
		this.messageName = messageName;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public String getInstID() {
		return instID;
	}

	public void setInstID(String instID) {
		this.instID = instID;
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public String getBuyOrSell() {
		return buyOrSell;
	}

	public void setBuyOrSell(String buyOrSell) {
		this.buyOrSell = buyOrSell;
	}

	@Override
	public String toString() {
		return "InputBean [messageName=" + messageName + ", tid=" + tid
				+ ", num=" + num + ", clientID=" + clientID + ", instID="
				+ instID + ", memberID=" + memberID + ", buyOrSell="
				+ buyOrSell + "]";
	}
	
}
