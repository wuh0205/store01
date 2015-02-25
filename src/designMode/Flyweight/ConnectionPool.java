package designMode.Flyweight;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

public class ConnectionPool {
	/*公有属性*/  
    private String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";  
    private String username = "wuht";  
    private String password = "123123";  
    private String driverClassName = "oracle.jdbc.driver.OracleDriver";
    
    private Vector<Connection> pool;
    private int poolSize=100;
    public static ConnectionPool instance;
    Connection conn=null;
    
    private ConnectionPool(){
    	pool=new Vector<Connection>(poolSize);
    	for(int i=0;i<poolSize;i++){
    		try {
    			Class.forName(driverClassName);
    			conn=DriverManager.getConnection(url, username, password);
    		} catch (ClassNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}	catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		pool.add(conn);
    	}
    	
    }
        
	public static ConnectionPool getInstance() {
		if(instance==null){
			synchronized (ConnectionPool.class) {
				if(instance==null){
					instance=new ConnectionPool();
				}
			}
		}
		return instance;
	}
	
	public synchronized Connection getConnection(){
		if(poolSize>0){
			Connection conn=pool.get(0);
			pool.remove(conn);
			return conn;
		}
		return null;
	}
	
	public synchronized void release(Connection conn){
		pool.add(conn);
	}

	public int getPoolSize() {
		return poolSize;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionPool.getInstance().getConnection();
		System.out.println(conn.toString()+"链接数："+ConnectionPool.getInstance().getPoolSize());
		ConnectionPool.getInstance().release(conn);
	}

}
