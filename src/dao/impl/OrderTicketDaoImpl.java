package dao.impl;

import java.sql.SQLException;
import java.util.List;
import entity.OrderTicket;

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
	public List<OrderTicket> listOrderTicket()  throws SQLException{
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
