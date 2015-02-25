package designMode.Strategy;

public abstract class AbstractCalculator {
	
	public int[] split(String exp,String opt){
		String[] arr=exp.split(opt);
		int[] intArr=new int[arr.length];
		for(int i=0;i<arr.length;i++){
			intArr[i]=Integer.parseInt(arr[i]);
		}
		return intArr;
		
	}
	
	public static void main(String[] args){
		ICalculator plus=new Plus();
		ICalculator minus=new Minus();
		System.out.println(plus.calculate("1+2"));
		System.out.println(minus.calculate("1-2"));
		
	}

}
