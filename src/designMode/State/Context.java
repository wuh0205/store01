package designMode.State;

public class Context {
	
	private State state;
	
	public Context(State state){
		this.state=state;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	public void method(){
		String stateValue=state.getValue();
		if(stateValue.equals("state1")){
			state.method1();
		}else if(stateValue.equals("state2")){
			state.method2();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		State state=new State();
		Context context=new Context(state);
		state.setValue("state1");
		context.method();
		state.setValue("state2");
		context.method();
	}

}
