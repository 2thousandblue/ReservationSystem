package dao;

import java.sql.SQLException;
import java.util.List;
import entity.OrderTicket;

/**
 * 订票信息Dao接口
 * @author 我的袜子都是洞
 * @date: 2019-01-18
 */
public interface OrderTicketDao {
	/**
	 * 创建订票信息
	 * @param orderTicket
	 * @return 创建结果
	 */
	boolean saveOrderTicket(OrderTicket orderTicket) throws SQLException;
	/**
	 * 删除订票信息
	 * @param id
	 * @return 删除结果
	 */
	boolean delOrderTicket(int id) throws SQLException;
	/**
	 * 更新订票信息
	 * @param orderTicket
	 * @return 更新结果
	 */
	boolean updateOrderTicket(OrderTicket orderTicket) throws SQLException;
	/**
	 * 获得某个订票信息
	 * @param id
	 * @return 订票信息对象
	 */
	OrderTicket getOrderTicket(int id) throws SQLException;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	boolean isUseFlightID(int id, String username) throws SQLException;
	/**
	 * 获得所有订票信息
	 * @return 订票信息对象数组
	 * @throws SQLException 
	 */
	List<OrderTicket> listOrderTicket() throws SQLException;
	/**
	 * 获取某用户的所有订单
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	List<OrderTicket> listOrderTicket(String username) throws SQLException;
}
