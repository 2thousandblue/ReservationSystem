package manage.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import dao.FlightDao;
import dao.OrderTicketDao;
import dao.UserDao;
import dao.impl.FlightDaoImpl;
import dao.impl.OrderTicketDaoImpl;
import dao.impl.UserDaoImpl;
import util.Transaction;
import util.TransactionImpl;
import entity.Flight;
import entity.OrderTicket;
import entity.User;
import exception.OrderTicketException;
import manage.OrderTicketManage;
/**
 * @Description: 订票信息管理实现
 * @author: 我的袜子都是洞
 * @date: 2019-01-21 11:45
 */
public class OrderTicketManageImpl implements OrderTicketManage {
	private Transaction transaction = new TransactionImpl ();
	private UserDao userDao = new UserDaoImpl ();
	private FlightDao flightDao = new FlightDaoImpl ();
	private OrderTicketDao orderTicketDao = new OrderTicketDaoImpl ();

	/* (non-Javadoc)
	 * @see manage.OrderTicketManage#changeTicket(java.lang.String, int)
	 */
	@Override
	public boolean changeTicket(int orderTicketID, int flightID) throws OrderTicketException {
		try {
			transaction.start();
			/**
			 * 获取需要改签的订单信息
			 */
			OrderTicketDao orderTicketDao = new OrderTicketDaoImpl();
			// 订单对象
			OrderTicket oldOrderTicket = orderTicketDao.getOrderTicket(orderTicketID);
			
			// 订单对象对应的航班号
			int oldFlightID = oldOrderTicket.getFlight_id();
			// 根据旧的航班号找到对应航班
			Flight flightOld = flightDao.getFlight(oldFlightID);
			// 使其航班票数恢复
			int num = flightOld.getTicket()+1;
			flightOld.setTicket(num+1);
			boolean isSuccess1 = flightDao.updateFlight(flightOld);
			
			// 获取新的航班信息
			Flight flight = flightDao.getFlight(flightID);
			
			int count = flight.getTicket();
			if (count <= 0) {
				transaction.commit();
				throw new OrderTicketException("票已售光");
			}
			// 减掉一张票
			flight.setTicket(count-1);
			boolean isSuccess2 = flightDao.updateFlight(flight);
			
			// 新的订单信息
			OrderTicket orderTicket = new OrderTicket();
			// 使用旧的ID
			orderTicket.setId(oldOrderTicket.getId());
			// 新的航班号
			orderTicket.setFlight_id(oldOrderTicket.getFlight_id());
			// 新的起飞时间
			orderTicket.setTakeoff_time(flight.getTakeoff_time());
			// 新的出发地
			orderTicket.setStart_place(flight.getStart_place());
			// 新的目的地
			orderTicket.setEnd_place(flight.getEnd_place());
			// 新的票价
			orderTicket.setPrice(flight.getPrice());
			// 旧的名字
			orderTicket.setUsername(oldOrderTicket.getUsername());
			// 旧的身份证
			orderTicket.setIdentity(oldOrderTicket.getIdentity());
			
			boolean isSuccess3 = orderTicketDao.updateOrderTicket(orderTicket);
			
			if (!(isSuccess1 && isSuccess2 && isSuccess3)) {
				transaction.rollback();
			}
			transaction.commit();
			return true;
		} catch (SQLException e) {
			throw new OrderTicketException(e.getMessage());
		}
	}

	@Override
	public boolean bookTicket(String loginname, int flightId) throws OrderTicketException {
		try {
			transaction.start();
			
			// 获取用户的相关信息
			User user = userDao.getUser(loginname);
			// 获取对应航班号的航班信息
			Flight flight = flightDao.getFlight(flightId);
			
			OrderTicketDaoImpl orderTicketDaoImpl = new OrderTicketDaoImpl ();
			boolean isUsed = orderTicketDaoImpl.isUseFlightID(flightId,user.getUsername());
			if (isUsed) {
				throw new OrderTicketException("您已买过这个航班的票了");
			}
			
			OrderTicket orderTicket = new OrderTicket();
			// 信息装填
			orderTicket.setFlight_id(flightId);
			orderTicket.setTakeoff_time(flight.getTakeoff_time());
			orderTicket.setStart_place(flight.getStart_place());
			orderTicket.setEnd_place(flight.getEnd_place());
			orderTicket.setPrice(flight.getPrice());
			orderTicket.setUsername(user.getUsername());
			orderTicket.setIdentity(user.getIdentity());
			int count = flight.getTicket();
			if (count <= 0) {
				transaction.commit();
				throw new OrderTicketException("票已售光");
			}
			// 减掉一张票
			flight.setTicket(count-1);
			boolean isSuccess1 = flightDao.updateFlight(flight);
			boolean isSuccess2 = orderTicketDao.saveOrderTicket(orderTicket);
			/**
			 * 还要从航班信息上减掉一张票
			 */
			if (!(isSuccess1 && isSuccess2)) {
				transaction.rollback();
			}
			
			transaction.commit();
			return true;
		} catch (SQLException e) {
			throw new OrderTicketException(e.getMessage());
		}
	}
	
	@Override
	public boolean cancerTicket(int id) throws OrderTicketException {
		try {
			transaction.start();
			OrderTicketDao ticketDao = new OrderTicketDaoImpl ();
			FlightDao flightDao = new FlightDaoImpl ();
			OrderTicket ticket = ticketDao.getOrderTicket(id);
			Flight flight = flightDao.getFlight(ticket.getFlight_id());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				
				Date takeOffTime= sdf.parse(flight.getTakeoff_time());
				// 判断起飞时间是否在当前时间之前
				boolean f = takeOffTime.before(new Date());
				if (f) {
					int ticketCount = flight.getTicket();
					// 恢复航班信息，机票+1
					flight.setTicket(ticketCount+1);
					boolean isSccuess1 = flightDao.updateFlight(flight);
					boolean isSccuess2 = ticketDao.delOrderTicket(id);
					if (!(isSccuess1 && isSccuess2)) {
						transaction.rollback();
					}
					return true;
				} else {
					throw new OrderTicketException ("已超时：禁止退票");
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}

			transaction.commit();
			return true;
		} catch (SQLException e) {
			throw new OrderTicketException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see manage.OrderTicketManage#listOrderTicket(java.lang.String)
	 */
	@Override
	public List<OrderTicket> listOrderTicket(String username) throws OrderTicketException {
		OrderTicketDao orderTicketDao = new OrderTicketDaoImpl();
		try {
			List<OrderTicket> list = orderTicketDao.listOrderTicket(username);
			if (list == null) {
				throw new OrderTicketException("没有订票信息");
			}
			return list;
		} catch (SQLException e) {
			throw new OrderTicketException(e.getMessage());
		}
	}
	
	

}
