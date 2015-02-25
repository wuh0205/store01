package designMode.Iterator;

import java.util.Arrays;

public class MyList implements Collection{
	
	private Object[] elementData;
	private int size=0;
	
	public MyList(){
		this(10);
	}
	
	public MyList(int i){
		this.elementData=new Object[i];
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return new MyIterator(this);
	}
	
	@Override
	public boolean add(Object obj) {
		// TODO Auto-generated method stub
		if(size+1-elementData.length>0){
			elementData=Arrays.copyOf(elementData, size+10);
		}
		elementData[size++]=obj;
		return true;
	}

	@Override
	public Object get(int i) {
		// TODO Auto-generated method stub
		return elementData[i];
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return elementData.length;
	}
	
	public static void main(String[] args){
		Collection collection=new MyList();
		collection.add("a1");
		collection.add("a2");
		collection.add("a3");
		Iterator iterator=collection.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		
	}

}
