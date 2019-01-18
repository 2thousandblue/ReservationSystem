package dao.impl;

import java.sql.SQLException;
import java.util.List;

import entity.User;

public class UserDaoImplTest {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		UserDaoImpl dao = new UserDaoImpl();
		
		List<User> u = dao.listUser();
		for (User u1:u) {
			System.out.println(u1);
		}
	}

}
