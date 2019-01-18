package dao.impl;

import java.sql.SQLException;

import dao.FlightDao;
import entity.Flight;

public class FlightDaoImpl implements FlightDao {
	/**
	 * 删除航班信息
	 * @param 航班信息对象
	 * @return 删除结果
	 */
	public boolean delFlight(int id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * 获取某个航班信息对象
	 * @param id
	 * @return 航班信息对象
	 */
	public Flight getFlight(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 获取所有航班信息
	 * @return 航班信息对象数组
	 */
	public Flight[] listFlight()  throws SQLException{
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 保存航班信息
	 * @param 航班信息对象
	 * @return 保存结果
	 */
	public boolean saveFlight(Flight flight) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * 更新航班信息
	 * @param 航班信息对象
	 * @return 更新结果
	 */
	public boolean updateFlight(Flight flight) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
