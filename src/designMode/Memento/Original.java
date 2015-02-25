package designMode.Memento;

public class Original {
	
	private String value;
	
	public Original(String value){
		this.value=value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public Memento createMemento(){
		return new Memento(value);
	}
	
    public void restoreMemento(Storage storage){
    	this.value=storage.getMemento().getValue();
    }
    
    
    public static void main(String[] args){
    	Original original=new Original("egg");
    	System.out.println(original.getValue());
    	Storage storage=new Storage(original.createMemento());
    	original.setValue("Dragon");
    	System.out.println(original.getValue());
    	original.restoreMemento(storage);
    	System.out.println(original.getValue());
    }

}
