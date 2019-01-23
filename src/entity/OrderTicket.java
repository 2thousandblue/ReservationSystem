package entity;

public class OrderTicket {
	// 订单号
	private int id;
	// 航班号
	private int flight_id;
	// 出发时间
	private String takeoff_time;
	// 出发地
	private String start_place;
	// 目的地
	private String end_place;
	// 票价
	private double price;
	// 姓名
	private String username;
	// 身份证号
	private String identity;
	// 够票loginname
	private String loginname;
	
	public OrderTicket() {
		super();
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the loginname
	 */
	public String getLoginname() {
		return loginname;
	}
	/**
	 * @param loginname the loginname to set
	 */
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public int getFlight_id() {
		return flight_id;
	}

	public void setFlight_id(int flight_id) {
		this.flight_id = flight_id;
	}

	/**
	 * @return the takeoff_time
	 */
	public String getTakeoff_time() {
		return takeoff_time;
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
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @return the identity
	 */
	public String getIdentity() {
		return identity;
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
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @param identity the identity to set
	 */
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrderTicket [end_place=" + end_place + ", flight_id=" + flight_id + ", id=" + id + ", identity=" + identity + ", loginname=" + loginname + ", price=" + price + ", start_place="
				+ start_place + ", takeoff_time=" + takeoff_time + ", username=" + username + "]";
	}
	
	
}
