package com.wuh.test.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wuh.test.bean.User;

@Repository
public class JdbcTemplateTestDAO {
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 查
	 * @param id
	 * @return
	 */
	public Boolean isExistsUser(Long id){
		String sql="SELECT COUNT(*) FROM USERS s WHERE S.U_ID="+String.valueOf(id);
		Integer count=jdbcTemplate.queryForObject(sql, Integer.class);
		if(count==1){
			return true;
		}
		return false;
	}

	/**
	 * 查
	 * @param id
	 * @return
	 */
	public User queryUserById(Long id) {
		String sql = "SELECT * FROM USERS u WHERE u.u_id ="
				+ String.valueOf(id);
		User user = getOBySql(sql).get(0);
		return user;
	}
	
	/**
	 * 增
	 * @param user
	 */
	public void saveUser(User user){
		String sql = "INSERT INTO USERS VALUES (?,?,?,?,?)";
		Object[] arr={user.getU_Id(),user.getName(),user.getAge(),user.getAddress(),user.getPassword()};
		jdbcTemplate.update(sql, arr);
	}
	
	/**
	 * 改
	 * @param user
	 */
	public void updateUser(User user){
		String sql = "UPDATE USERS s SET s.name=?,s.age=?,s.address=?,s.password=? WHERE s.password=?";
		Object[] arr={user.getName(),user.getAge(),user.getAddress(),user.getPassword(),user.getU_Id()};
		jdbcTemplate.update(sql, arr);
	}
	
	/**
	 * 删
	 * @param user
	 */
	public void deleteUser(User user){
		String sql="DELETE FROM USERS s  WHERE s.u_id="+user.getU_Id();
		jdbcTemplate.execute(sql);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getOBySql(String sql) {

		@SuppressWarnings("rawtypes")
		class UserRowMapper implements RowMapper {

			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				User u = new User();
				u.setU_Id(Long.parseLong(rs.getString("u_id")));
				u.setName(rs.getString("name"));
				u.setAge(rs.getString("age"));
				u.setPassword(rs.getString("password"));
				return u;
			}

		}
		return jdbcTemplate.query(sql, new UserRowMapper());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
