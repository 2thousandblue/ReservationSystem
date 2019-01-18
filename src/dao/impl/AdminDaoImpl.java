package dao.impl;

import java.sql.SQLException;
import dao.AdminDao;
import entity.Admin;

/**
 * @author 我的袜子都是洞
 *
 */
public class AdminDaoImpl implements AdminDao {

	/**
	 * 删除管理员
	 * @param admin
	 * @return
	 */
	public boolean delAdmin(int id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 获得管理员
	 * @return 管理员对象
	 */
	public Admin getAdmin(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 获得所有管理员
	 * @return 管理员对象数组
	 */
	public Admin[] listAdmin() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 创建管理员
	 * @param 管理员对象
	 * @return 创建结果
	 */
	public boolean saveAdmin(Admin admin) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 更新管理员信息
	 * @param 管理员对象
	 * @return
	 */
	public boolean updateAdmin(Admin admin) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
