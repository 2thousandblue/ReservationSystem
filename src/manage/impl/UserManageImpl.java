package manage.impl;

import java.sql.SQLException;
import java.util.regex.Pattern;
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
	 * @param user
	 * @return 验证结果
	 */
	@Override
	public boolean loginUser(String loginname, String password1,String password2) throws UserException {
		if (loginname == null) {
			throw new UserException("用户名为空");
		}
		if ("".equals(password1) || "".equals(password2)) {
			throw new UserException("密码为空");
		}
		if (password1.equals(password2)) {
			throw new UserException("两次密码不一致");
		}
		// 以字母开头，长度在6~18之间，只能包含字符、数字和下划线
		String pattern = "^[a-zA-Z]\\w{5,17}$";
		boolean isMatch = Pattern.matches(pattern, loginname);
		if (!isMatch) {
			throw new UserException("用户名：以字母开头，长度在6~18之间，只能包含字符、数字和下划线");
		}
		UserDao userDao = new UserDaoImpl();
		try {
			User user = userDao.getUser(loginname);
			if (user.getPassword().equals(password1)) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			throw new UserException("你输入的帐号不存在");
		}
	}
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	@Override
	public boolean registerUser(String loginname, String password1,String password2) throws UserException {
		return false;
	}

}
