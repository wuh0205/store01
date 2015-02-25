package com.wuh.test.bean;

public class LogLine implements Comparable<LogLine>{
	
	private int num;
	
	private int type;
	
	private String content;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public int compareTo(LogLine o) {
		// TODO Auto-generated method stub
		if (o.getNum() == this.getNum()) {
            if (o.getType() > this.getType()) {
                return 1;
            } else {
                return -1;
            }
        } else {
            if (o.getNum() > this.getNum()) {
                return -1;
            } else {
                return 1;
            }
        }
	}
	
}
