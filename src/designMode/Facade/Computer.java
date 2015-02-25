package designMode.Facade;

/**
 * 外观模式
 * @author wuh
 *
 */
public class Computer {
	
	private CPU cpu;
	private Memory memory;
	private Disk disk;
	
	public Computer(){
		this.cpu=new CPU();
		this.memory=new Memory();
		this.disk=new Disk();
	}
	
	public void startup(){
		cpu.startup();
		memory.startup();
		disk.startup();
	}
	
	
	public void shutdown(){
		cpu.shutdown();
		memory.shutdown();
		disk.shutdown();
	}

	public void run(){
		cpu.run();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Computer c=new Computer();
		c.startup();
		c.run();
		c.shutdown();

	}

}
