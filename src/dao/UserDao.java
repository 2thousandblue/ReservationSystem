package dao;

import java.sql.SQLException;
import java.util.List;

import entity.User;

/**
 * 用户Dao接口
 * @author 我的袜子都是洞
 * @date: 2019-01-18
 */
public interface UserDao {
	/**
	 * 创建用户
	 * @param 用户对象
	 * @return 创建结果
	 */
	boolean saveUser(User user) throws SQLException;
	/**
	 * 删除用户
	 * @param 用户对象
	 * @return 删除结果
	 */
	boolean delUser(int id) throws SQLException;
	/**
	 * 更新用户数据
	 * @param 用户对象
	 * @return 更新结果
	 */
	boolean updateUser(User user) throws SQLException;
	/**
	 * 获得某用户
	 * @param id
	 * @return 用户对象
	 */
	User getUser(int id) throws SQLException;
	/**
	 * 获得某用户
	 * @param loginname
	 * @return 用户对象
	 */
	User getUser(String loginname) throws SQLException;
	/**
	 * 获得所有用户
	 * @return 用户对象数组
	 * @throws SQLException 
	 */
	List<User> listUser() throws SQLException;
}
