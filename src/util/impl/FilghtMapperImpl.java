package util.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import entity.Flight;
import util.RowMapperObject;
/**
 * 航班信息对象映射
 * @author 我的袜子都是洞
 * @date: 2019-01-18
 */
public class FilghtMapperImpl implements RowMapperObject {

	@Override
	public Object rowMapperObject(ResultSet rs) throws SQLException {
		Flight flight = new Flight();
		flight.setId(rs.getInt("id"));
		flight.setTakeoff_time(rs.getString("takeoff_time"));
		flight.setFlying_time(rs.getString("flying_time"));
		flight.setStart_place(rs.getString("start_place"));
		flight.setEnd_place(rs.getString("end_place"));
		flight.setTicket(rs.getInt("ticket"));
		flight.setPrice(rs.getDouble("price"));
		return flight;
	}

}
