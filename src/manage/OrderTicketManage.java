package manage;
import entity.OrderTicket;
import exception.OrderTicketException;
/**
 * @Description: 订票信息管理接口
 * @author: 我的袜子都是洞
 * @date: 2019-01-19 14:00
 */
public interface OrderTicketManage {
	/**
	 * 订票
	 * @param orderTicket 订票信息
	 * @return 订票号
	 */
	int bookTicket (OrderTicket orderTicket) throws OrderTicketException;
	/**
	 * 取消订票
	 * @param orderTicket
	 * @return
	 * @throws OrderTicketException
	 */
	boolean cancerTicket (OrderTicket orderTicket) throws OrderTicketException;
}
