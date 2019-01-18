package entity;
/**
 * 航班信息
 * @author 我的袜子都是洞
 * @date: 2019-01-18
 */
public class Flight {
	// 航班号
	private int id;
	// 出发时间
	private String takeoff_time;
	// 飞行时间
	private String flying_time;
	// 出发地
	private String start_place;
	// 目的地
	private String end_place;
	// 余票
	private int ticket;
	// 票价
	private double price;
	public Flight() {
		super();
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the takeoff_time
	 */
	public String getTakeoff_time() {
		return takeoff_time;
	}
	/**
	 * @return the flying_time
	 */
	public String getFlying_time() {
		return flying_time;
	}
	/**
	 * @return the start_place
	 */
	public String getStart_place() {
		return start_place;
	}
	/**
	 * @return the end_place
	 */
	public String getEnd_place() {
		return end_place;
	}
	/**
	 * @return the ticket
	 */
	public int getTicket() {
		return ticket;
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @param takeoffTime the takeoff_time to set
	 */
	public void setTakeoff_time(String takeoffTime) {
		takeoff_time = takeoffTime;
	}
	/**
	 * @param flyingTime the flying_time to set
	 */
	public void setFlying_time(String flyingTime) {
		flying_time = flyingTime;
	}
	/**
	 * @param startPlace the start_place to set
	 */
	public void setStart_place(String startPlace) {
		start_place = startPlace;
	}
	/**
	 * @param endPlace the end_place to set
	 */
	public void setEnd_place(String endPlace) {
		end_place = endPlace;
	}
	/**
	 * @param ticket the ticket to set
	 */
	public void setTicket(int ticket) {
		this.ticket = ticket;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Flight [end_place=" + end_place + ", flying_time=" + flying_time + ", id=" + id + ", price=" + price + ", start_place=" + start_place
				+ ", takeoff_time=" + takeoff_time + ", ticket=" + ticket + "]";
	}
	
}
