package dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dao.FlightDao;
import entity.Flight;
import util.JDBCUtil;
import util.impl.FilghtMapperImpl;
/**
 * @Description: 航班信息实现类
 * @author: 我的袜子都是洞
 * @date: 2019-01-18 20:06
 */
public class FlightDaoImpl implements FlightDao {
	
	/* (non-Javadoc)
	 * @see dao.FlightDao#listCanModify()
	 */
	@Override
	public List<Flight> listCanModify() throws SQLException {
		String sql = "SELECT *  FROM flight WHERE takeoff_time > NOW() AND id NOT IN (SELECT flight_id FROM order_ticket)";
		try {
			List<Flight> listFlight = new ArrayList<Flight>();
			List<Object> objects = JDBCUtil.executeQuery(sql, new FilghtMapperImpl());
			for (Object o:objects) {
				listFlight.add((Flight) o);
			}
			return listFlight;
		} catch (SQLException e) {
			throw new SQLException("无法执行该操作，请联系管理人员");
		}
	}
	/**
	 * 删除航班信息
	 * @param id 航班信息对象
	 * @return 删除结果
	 */
	@Override
	public boolean delFlight(int id) throws SQLException {
		String sql = "DELETE FROM  flight WHERE id=?";
		try {
			int i = JDBCUtil.executeUpdate(sql, id);
			if (i == 0) {
				throw new SQLException("航班信息不存在");
			} else {
				return true;
			}
		} catch (SQLException e) {
			throw new SQLException("无法执行该操作，请联系管理人员");
		}
	}
	/**
	 * 获取某个航班信息对象
	 * @param id
	 * @return 航班信息对象
	 */
	@Override
	public Flight getFlight(int id) throws SQLException {
		String sql = "SELECT *  FROM flight WHERE id=?";
		try {
			List<Flight> listFlight = new ArrayList<Flight>();
			List<Object> objects = JDBCUtil.executeQuery(sql, new FilghtMapperImpl(), id);
			for (Object o:objects) {
				listFlight.add((Flight)o);
			}
			return listFlight.get(0);
		} catch (SQLException e) {
			throw new SQLException("无法执行该操作，请联系管理人员");
		}
	}
	
	
	/* (non-Javadoc)
	 * @see dao.FlightDao#listCanFlight()
	 */
	@Override
	public List<Flight> listCanFlight() throws SQLException {
		String sql = "SELECT *  FROM flight WHERE takeoff_time > NOW()";
		try {
			List<Flight> listFlight = new ArrayList<Flight>();
			List<Object> objects = JDBCUtil.executeQuery(sql, new FilghtMapperImpl());
			for (Object o:objects) {
				listFlight.add((Flight) o);
			}
			return listFlight;
		} catch (SQLException e) {
			throw new SQLException("无法执行该操作，请联系管理人员");
		}
	}
	/**
	 * 获取所有航班信息
	 * @return 航班信息对象数组
	 */
	@Override
	public List<Flight> listFlight()  throws SQLException{
		String sql = "SELECT *  FROM flight";
		try {
			List<Flight> listFlight = new ArrayList<Flight>();
			List<Object> objects = JDBCUtil.executeQuery(sql, new FilghtMapperImpl());
			for (Object o:objects) {
				listFlight.add((Flight) o);
			}
			return listFlight;
		} catch (SQLException e) {
			throw new SQLException("无法执行该操作，请联系管理人员");
		}
	}
	/**
	 * 保存航班信息
	 * @param flight 航班信息对象
	 * @return 保存结果
	 */
	@Override
	public boolean saveFlight(Flight flight) throws SQLException {
		String sql = "INSERT INTO flight (takeoff_time, flying_time, start_place, end_place, ticket, price) VALUES (?,?,?,?,?,?)";
		try {
			int i = JDBCUtil.executeUpdate(sql,
					flight.getTakeoff_time(),
					flight.getFlying_time(),
					flight.getStart_place(),
					flight.getEnd_place(),
					flight.getTicket(),
					flight.getPrice());
			if (i == 1) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			throw new SQLException("无法执行该操作，请联系管理人员");
		}
	}
	/**
	 * 更新航班信息
	 * @param flight 航班信息对象
	 * @return 更新结果
	 */
	@Override
	public boolean updateFlight(Flight flight) throws SQLException {
		String sql = "UPDATE flight SET takeoff_time=?, flying_time=?, start_place=?, end_place=?, ticket=?, price=? WHERE id=?";
		try {
			int i = JDBCUtil.executeUpdate(sql,
					flight.getTakeoff_time(),
					flight.getFlying_time(),
					flight.getStart_place(),
					flight.getEnd_place(),
					flight.getTicket(),
					flight.getPrice(),
					flight.getId());
			if (i == 1) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			throw new SQLException("无法执行该操作，请联系管理人员");
		}
	}

}
