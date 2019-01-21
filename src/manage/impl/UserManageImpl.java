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
}
