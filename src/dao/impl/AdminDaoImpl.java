package dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dao.AdminDao;
import entity.Admin;
import util.JDBCUtil;
import util.impl.AdminMapperImpl;
import util.impl.UserMapperImpl;

/**
 * @Description: 管理员Dao实现类
 * @author: 我的袜子都是洞
 * @date: 2019-01-18 19:47
 */
public class AdminDaoImpl implements AdminDao {
	/**
	 * 删除管理员
	 * @param id
	 * @return
	 */
	@Override
	public boolean delAdmin(int id) throws SQLException {
		String sql = "DELETE FROM admin WHERE id=?";
		try {
			int i = JDBCUtil.executeUpdate(sql, id);
			if (i == 0) {
				throw new SQLException("管理员不存在");
			} else {
				return true;
			}
		} catch (SQLException e) {
			throw new SQLException("无法执行该操作");
		}
	}

	/**
	 * 获得管理员
	 * @return 管理员对象
	 */
	@Override
	public Admin getAdmin(String loginname) throws SQLException {
		String sql = "SELECT *  FROM admin WHERE loginname=?";
		try {
			List<Admin> listAdmin = new ArrayList<Admin>();
			List<Object> objects = JDBCUtil.executeQuery(sql, new AdminMapperImpl(), loginname);
			if (objects.size() == 0) {
				return null;
			}
			for (Object o:objects) {
				listAdmin.add((Admin)o);
			}
			return listAdmin.get(0);
		} catch (SQLException e) {
			throw new SQLException("无法执行该操作，请联系开发人员");
		}
	}

	/**
	 * 获得所有管理员
	 * @return 管理员对象数组
	 */
	@Override
	public List<Admin> listAdmin() throws SQLException {
		String sql = "SELECT *  FROM admin";
		try {
			List<Admin> listAdmin = new ArrayList<Admin>();
			List<Object> objects = JDBCUtil.executeQuery(sql, new UserMapperImpl());
			if (objects.size() == 0) {
				return null;
			}
			for (Object o:objects) {
				listAdmin.add((Admin) o);
			}
			return listAdmin;
		} catch (SQLException e) {
			throw new SQLException("无法执行该操作，请联系开发人员");
		}
	}

	/**
	 * 创建管理员
	 * @param admin 管理员对象
	 * @return 创建结果
	 */
	@Override
	public boolean saveAdmin(Admin admin) throws SQLException {
		String sql = "INSERT INTO admin (loginname, password, username, identity, phone) VALUES (?,?,?,?,?)";
		try {
			int i = JDBCUtil.executeUpdate(sql, admin.getLoginname(), admin.getPassword(), admin.getUsername(), admin.getIdentity(), admin.getPhone());
			if (i == 1) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			throw new SQLException("无法执行该操作，请联系开发人员");
		}
	}

	/**
	 * 更新管理员信息
	 * @param admin 管理员对象
	 * @return
	 */
	@Override
	public boolean updateAdmin(Admin admin) throws SQLException {
		String sql = "UPDATE admin SET loginname=?, password=?, username=?, identity=?, phone=? WHERE id=?";
		try {
			int i = JDBCUtil.executeUpdate(sql,
					admin.getLoginname(),
					admin.getPassword(),
					admin.getUsername(),
					admin.getIdentity(),
					admin.getPhone(),
					admin.getId());
			if (i == 1) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			throw new SQLException("无法执行该操作，请联系开发人员");
		}
	}

}
