package manage.impl;

import java.sql.SQLException;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import entity.User;
import exception.UserException;
import manage.UserManage;
/**
 * @Description: 用户业务实现
 * @author: 我的袜子都是洞
 * @date: 2019-01-19 15:36
 */
public class UserManageImpl implements UserManage {
	
	/* (non-Javadoc)
	 * @see manage.UserManage#updateUser(entity.User)
	 */
	@Override
	public boolean updateUser(User user) throws UserException {
		UserDao userDao = new UserDaoImpl();
		try {
			boolean f = userDao.updateUser(user);
			return f;
		} catch (SQLException e) {
			throw new UserException(e.getMessage());
		}
	}
	/* (non-Javadoc)
	 * @see manage.UserManage#getUserInfo(java.lang.String)
	 */
	@Override
	public User getUserInfo(String loginname) throws UserException {
		UserDao userDao = new UserDaoImpl();
		try {
			 User user = userDao.getUser(loginname);
			 if (user == null) {
				 throw new UserException("获取用户失败");
			 }
			 return user;
		} catch (SQLException e) {
			throw new UserException(e.getMessage());
		}
	}
	/**
	 * 用户登陆验证
	 * @return 验证结果
	 */
	@Override
	public boolean loginUser(String loginname, String password) throws UserException {
		UserDao userDao = new UserDaoImpl();
		try {
			User user = userDao.getUser(loginname);
			if (user == null) {
				throw new UserException("你输入的帐号不存在");
			}
			if (user.getPassword().equals(password)) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			throw new UserException(e.getMessage());
		}
	}
	/**
	 * 用户注册
	 * @return
	 */
	@Override
	public boolean registerUser(User user) throws UserException {
		UserDao userDao = new UserDaoImpl();
		try {
			 boolean f = userDao.saveUser(user);
			 return f;
		} catch (SQLException e) {
			throw new UserException(e.getMessage());
		}
	}
	/* (non-Javadoc)
	 * @see manage.UserManage#getUsername(java.lang.String)
	 */
	@Override
	public String getUsername(String loginname) throws UserException {
		UserDao userDao = new UserDaoImpl();
		try {
			 String username = (userDao.getUser(loginname)).getUsername();
			 return username;
		} catch (SQLException e) {
			throw new UserException(e.getMessage());
		}
	}
	
}
