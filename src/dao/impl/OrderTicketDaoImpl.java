package dao.impl;

import java.sql.SQLException;

import entity.OrderTicket;

public class OrderTicketDaoImpl implements dao.OrderTicketDao {
	/**
	 * 删除订票信息
	 * @param orderTicket
	 * @return 删除结果
	 */
	public boolean delOrderTicket(int id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * 获得某个订票信息
	 * @param id
	 * @return 订票信息对象
	 */
	public OrderTicket getOrderTicket(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 获得所有订票信息
	 * @return 订票信息对象数组
	 */
	public OrderTicket[] listOrderTicket()  throws SQLException{
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 创建订票信息
	 * @param orderTicket
	 * @return 创建结果
	 */
	public boolean saveOrderTicket(OrderTicket orderTicket)  throws SQLException{
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * 更新订票信息
	 * @param orderTicket
	 * @return 更新结果
	 */
	public boolean updateOrderTicket(OrderTicket orderTicket) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
