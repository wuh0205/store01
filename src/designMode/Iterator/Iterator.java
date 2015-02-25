package designMode.Iterator;

public interface Iterator {
	
	/**
	 * 向前取元素
	 * @return
	 */
	public Object previous();
	
	/**
	 * 向后取元素
	 * @return
	 */
	public Object next();
	
	/**
	 * 查看后面是否有元素
	 * @return
	 */
	public boolean hasNext();
	
	/**
	 * 取第一个元素
	 * @return
	 */
	public Object first();
	

}
