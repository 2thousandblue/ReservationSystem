package dao;

import java.sql.SQLException;
import java.util.List;

import entity.Flight;

/**
 * 航班信息Dao接口
 * @author 我的袜子都是洞
 * @date: 2019-01-18
 */
public interface FlightDao {
	/**
	 * 保存航班信息
	 * @param flight 航班信息对象
	 * @return 保存结果
	 */
	boolean saveFlight(Flight flight) throws SQLException;
	/**
	 * 删除航班信息
	 * @param id 航班信息对象
	 * @return 删除结果
	 */
	boolean delFlight(int id) throws SQLException;
	/**
	 * 更新航班信息
	 * @param flight 航班信息对象
	 * @return 更新结果
	 */
	boolean updateFlight(Flight flight) throws SQLException;
	/**
	 * 获取某个航班信息对象
	 * @param id
	 * @return 航班信息对象
	 */
	Flight getFlight(int id) throws SQLException;
	/**
	 * 获取所有航班信息
	 * @return 航班信息对象数组
	 * @throws SQLException 
	 */
	List<Flight> listFlight() throws SQLException;
	List<Flight> listCanModify() throws SQLException;
	List<Flight> listCanFlight() throws SQLException;
}
