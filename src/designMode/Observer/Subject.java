package designMode.Observer;

public interface Subject {
	
	/**
	 * 增加观察者
	 * @param observer
	 */
	public void add(Observer observer);
	
	/**
	 * 删除观察者
	 * @param observer
	 */
	public void remove(Observer observer);
	
	/**
	 * 通知观察者
	 */
	public void notifyObservers();
	
	/**
	 * 操作自身
	 */
	public void operation();

}
