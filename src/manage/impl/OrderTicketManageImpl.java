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
	
	public static void main(String[] args) {
		OrderTicketManageImpl t = new OrderTicketManageImpl ();
		try {
			t.changeTicket(1, 4);
		} catch (OrderTicketException e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see manage.OrderTicketManage#changeTicket(java.lang.String, int)
	 */
	@Override
	public boolean changeTicket(int orderTicketID, int flightID) throws OrderTicketException {
		try {
			transaction.start();
			OrderTicketDao orderTicketDao = new OrderTicketDaoImpl();
			// 获取订单
			OrderTicket oldOrderTicket = orderTicketDao.getOrderTicket(orderTicketID);
			int oldFlightID = oldOrderTicket.getFlight_id();
			// 根据旧的航班号找到对应航班
			Flight flightOld = flightDao.getFlight(oldFlightID);
			// 恢复旧航班的票数
			int num = flightOld.getTicket();
			flightOld.setTicket(num+1);
			// 判断目标航班是否有票
			Flight flight = flightDao.getFlight(flightID);
			int count = flight.getTicket();
			if (count <= 0) {
				throw new OrderTicketException("票已售光");
			}
			// 减掉一张票
			flight.setTicket(count-1);
			// 更新订单信息
			OrderTicket orderTicket = new OrderTicket();
			orderTicket.setId(oldOrderTicket.getId());
			orderTicket.setFlight_id(flightID);
			orderTicket.setTakeoff_time(flight.getTakeoff_time());
			orderTicket.setStart_place(flight.getStart_place());
			orderTicket.setEnd_place(flight.getEnd_place());
			orderTicket.setPrice(flight.getPrice());
			orderTicket.setUsername(oldOrderTicket.getUsername());
			orderTicket.setIdentity(oldOrderTicket.getIdentity());
			orderTicket.setLoginname(oldOrderTicket.getLoginname());
			
			boolean isSuccess1 = flightDao.updateFlight(flightOld);
			boolean isSuccess2 = flightDao.updateFlight(flight);
			boolean isSuccess3 = orderTicketDao.updateOrderTicket(orderTicket);
			
			if ((!isSuccess1 && !isSuccess2 && !isSuccess3)) {
				transaction.rollback();
				return false;
			}else {
				transaction.commit();
				return true;
			}
			
		} catch (SQLException e) {
			throw new OrderTicketException(e.getMessage());
		}
	}

	
	
	@Override
	public boolean bookTicket(int flightId, String username,String identity, String loginname) throws OrderTicketException {
		try {
			transaction.start();
			// 获取对应航班号的航班信息
			Flight flight = flightDao.getFlight(flightId);
			
			OrderTicketDaoImpl orderTicketDaoImpl = new OrderTicketDaoImpl ();
			
			boolean isUsed = orderTicketDaoImpl.isUseFlightID(flightId,username);
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
			orderTicket.setUsername(username);
			orderTicket.setIdentity(identity);
			orderTicket.setLoginname(loginname);
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
			// 订单
			OrderTicket ticket = ticketDao.getOrderTicket(id);
			// 订单里的航班号
			Flight flight = flightDao.getFlight(ticket.getFlight_id());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Date takeOffTime= sdf.parse(flight.getTakeoff_time());
				// 判断起飞时间是否在当前时间之前
				boolean f = takeOffTime.after(new Date());
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
	public List<OrderTicket> listOrderTicket(String loginname) throws OrderTicketException {
		OrderTicketDao orderTicketDao = new OrderTicketDaoImpl();
		try {
			List<OrderTicket> list = orderTicketDao.listOrderTicket(loginname);
			if (list == null) {
				throw new OrderTicketException("没有订票信息");
			}
			return list;
		} catch (SQLException e) {
			throw new OrderTicketException(e.getMessage());
		}
	}
	
	

}
