package designMode.Strategy;

public class Minus extends AbstractCalculator implements ICalculator{

	@Override
	public int calculate(String str) {
		// TODO Auto-generated method stub
		int[] arr=split(str,"-");
		return arr[0]-arr[1];
	}

}
