package designMode.Strategy;

public class Plus extends AbstractCalculator implements ICalculator{

	@Override
	public int calculate(String str) {
		// TODO Auto-generated method stub
		int[] arr=super.split(str, "\\+");
		return arr[0]+arr[1];
	}

}
