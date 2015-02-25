package designMode.Iterator;

public interface Collection {
	
	public Iterator iterator();
	
	public boolean add(Object obj);
	
	/**
	 * 取得元素
	 * @param i
	 * @return
	 */
	public Object get(int i);
	
	/**
	 * 取得集合中元素个数
	 * @return
	 */
	public int size();

}
