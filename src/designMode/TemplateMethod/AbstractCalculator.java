package designMode.TemplateMethod;

public abstract class AbstractCalculator {
	public int calculate(String exp,String opt){
		int arr[]=split(exp,opt);
		return calculateHandle(arr[0],arr[1]);
	}
	
	abstract public int calculateHandle(int arr1,int arr2);
	
	public int[] split(String exp,String opt){
		String[] arr=exp.split(opt);
		int[] intArr=new int[arr.length];
		for(int i=0;i<arr.length;i++){
			intArr[i]=Integer.parseInt(arr[i]);
		}
		return intArr;
		
	}
	
	public static void main(String[] args){
		AbstractCalculator plus=new Plus();
		System.out.println(plus.calculate("8+8", "\\+"));
		AbstractCalculator minus=new Minus();
		System.out.println(minus.calculate("8-8", "-"));
	}

}
