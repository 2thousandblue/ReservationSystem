package util.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import util.RowMapperObject;
import entity.User;
/**
 * 用户对象映射
 * @author 我的袜子都是洞
 * @date: 2019-01-18
 */
public class UserMapperImpl implements RowMapperObject {

	@Override
	public Object rowMapperObject(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setLoginname(rs.getString("loginname"));
		user.setPassword(rs.getString("password"));
		user.setUsername(rs.getString("username"));
		user.setIdentity(rs.getString("identity"));
		user.setSex(rs.getString("sex"));
		user.setPhone(rs.getString("phone"));
		user.setEmail(rs.getString("email"));
		user.setAddress(rs.getString("address"));
		return user;
	}

}
