package dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.OrderTicket;
import util.JDBCUtil;
import util.impl.OrderTicketMapperImpl;
/**
 * @Description: 订票信息实现类
 * @author: 我的袜子都是洞
 * @date: 2019-01-18 20:06
 */
public class OrderTicketDaoImpl implements dao.OrderTicketDao {

	/**
	 * 删除订票信息
	 * @param id orderTicket
	 * @return 删除结果
	 */
	@Override
	public boolean delOrderTicket(int id) throws SQLException {
		String sql = "DELETE FROM order_ticket WHERE id=?";
		try {
			int i = JDBCUtil.executeUpdate(sql, id);
			if (i == 0) {
				throw new SQLException("购票信息不存在，删除失败");
			} else {
				return true;
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		}
	}
	/**
	 * 获得某个订票信息
	 * @param id
	 * @return 订票信息对象
	 */
	@Override
	public OrderTicket getOrderTicket(int id) throws SQLException {
		String sql = "SELECT *  FROM order_ticket WHERE id=?";
		try {
			List<OrderTicket> listOrderTicket = new ArrayList<OrderTicket>();
			List<Object> objects = JDBCUtil.executeQuery(sql, new OrderTicketMapperImpl(), id);
			if (objects==null || objects.size()==0) {
				throw new SQLException("订单不存在");
			}
			for (Object o:objects) {
				listOrderTicket.add((OrderTicket)o);
			}
			return listOrderTicket.get(0);
		} catch (SQLException e) {
			throw new SQLException(e);
		}
	}
	/**
	 * 获得所有订票信息
	 * @return 订票信息对象数组
	 */
	@Override
	public List<OrderTicket> listOrderTicket()  throws SQLException{
		String sql = "SELECT *  FROM order_ticket";
		try {
			List<OrderTicket> listOrderTicket = new ArrayList<OrderTicket>();
			List<Object> objects = JDBCUtil.executeQuery(sql, new OrderTicketMapperImpl());
			if (objects.size() == 0) {
				return null;
			}
			for (Object o:objects) {
				listOrderTicket.add((OrderTicket) o);
			}
			return listOrderTicket;
		} catch (SQLException e) {
			throw new SQLException("无法执行该操作，请联系管理人员");
		}
	}
	
	
	/* (non-Javadoc)
	 * @see dao.OrderTicketDao#getOrderTicketByFlightID(int)
	 */
	@Override
	public boolean isUseFlightID(int id, String username) throws SQLException {
		String sql = "SELECT *  FROM order_ticket WHERE flight_id=? AND username=?";
		try {
			List<Object> objects = JDBCUtil.executeQuery(sql, new OrderTicketMapperImpl(), id, username);
			if (objects == null || objects.size()==0) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			throw new SQLException("无法执行该操作，请联系管理人员");
		}
	}
	
	
	/**
	 * 创建订票信息
	 * @param 
	 * @return 创建结果
	 */
	@Override
	public boolean saveOrderTicket(OrderTicket orderTicket)  throws SQLException{
		String sql = "INSERT INTO order_ticket (flight_id, takeoff_time, start_place, end_place, price, username, identity, loginname) VALUES (?,?,?,?,?,?,?,?)";
		try {
			int i = JDBCUtil.executeUpdate(sql,
					orderTicket.getFlight_id(),
					orderTicket.getTakeoff_time(),
					orderTicket.getStart_place(),
					orderTicket.getEnd_place(),
					orderTicket.getPrice(),
					orderTicket.getUsername(),
					orderTicket.getIdentity(),
					orderTicket.getLoginname());
			if (i == 1) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			throw new SQLException("无法执行该操作，请联系管理人员");
		}
	}
	
	public static void main(String[] args) {
		OrderTicket a = new OrderTicket();
		a.setId(1);
		a.setFlight_id(4);
		a.setTakeoff_time("test");
		a.setStart_place("tt");
		a.setEnd_place("dsa");
		a.setPrice(50);
		a.setUsername("user1");
		a.setIdentity("121212121");
		a.setLoginname("user1");
		OrderTicketDaoImpl t = new OrderTicketDaoImpl ();
		
		try {
			boolean f = t.updateOrderTicket(a);
			System.out.println(f);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新订票信息
	 * @param orderTicket
	 * @return 更新结果
	 */
	@Override
	public boolean updateOrderTicket(OrderTicket orderTicket) throws SQLException {
		String sql = "UPDATE order_ticket SET flight_id=?,takeoff_time=?, start_place=?, end_place=?, price=?, username=?, identity=?, loginname=? WHERE id=?";
		try {
			int i = JDBCUtil.executeUpdate(sql,
					orderTicket.getFlight_id(),
					orderTicket.getTakeoff_time(),
					orderTicket.getStart_place(),
					orderTicket.getEnd_place(),
					orderTicket.getPrice(),
					orderTicket.getUsername(),
					orderTicket.getIdentity(),
					orderTicket.getLoginname(),
					orderTicket.getId());
			if (i == 1) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
	}
	/* (non-Javadoc)
	 * @see dao.OrderTicketDao#listOrderTicket(java.lang.String)
	 */
	@Override
	public List<OrderTicket> listOrderTicket(String loginname) throws SQLException {
		String sql = "SELECT *  FROM order_ticket WHERE loginname=?";
		try {
			List<OrderTicket> listOrderTicket = new ArrayList<OrderTicket>();
			List<Object> objects = JDBCUtil.executeQuery(sql, new OrderTicketMapperImpl(), loginname);
			if (objects.size() == 0) {
				return null;
			}
			for (Object o:objects) {
				listOrderTicket.add((OrderTicket) o);
			}
			return listOrderTicket;
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
	}
	
}
