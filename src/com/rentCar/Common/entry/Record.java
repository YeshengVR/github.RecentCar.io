package com.rentCar.Common.entry;

import java.util.Date;

	/**
	 * 租赁记录表
	 * @author HPK
	 *
	 */
public class Record {
	private Number id; //租赁记录编号 11位
	private Number User_id;//用户id编号 11位
	private String User_name;
	private Number Car_id;//租赁车id编号 11位
	private String Car_name;
	private Date Start_Date;//租车开始时间  
	private Date Return_Date;//换车时间
	private Number Payment;//付款金额 ，最大七位整数，两位小数
	public Record(String user_name, String car_name, Date start_Date, Date return_Date, Number payment) {
		super();
		User_name = user_name;
		Car_name = car_name;
		Start_Date = start_Date;
		Return_Date = return_Date;
		Payment = payment;
	}
	public String getUser_name() {
		return User_name;
	}
	public void setUser_name(String user_name) {
		User_name = user_name;
	}
	public String getCar_name() {
		return Car_name;
	}
	public void setCar_name(String car_name) {
		Car_name = car_name;
	}
	public Number getId() {
		return id;
	}
	public void setId(Number id) {
		this.id = id;
	}
	public Number getUser_id() {
		return User_id;
	}
	public void setUser_id(Number user_id) {
		User_id = user_id;
	}
	public Number getCar_id() {
		return Car_id;
	}
	public void setCar_id(Number car_id) {
		Car_id = car_id;
	}
	public Date getStart_Date() {
		return Start_Date;
	}
	public void setStart_Date(Date start_Date) {
		Start_Date = start_Date;
	}
	public Date getReturn_Date() {
		return Return_Date;
	}
	public void setReturn_Date(Date return_Date) {
		Return_Date = return_Date;
	}
	public Number getPayment() {
		return Payment;
	}
	public void setPayment(Number payment) {
		Payment = payment;
	}

	public String toStrings() {
		return "用户名:" + User_name + "     \t车牌号:" + Car_name + "\t租车开始时间:" + Start_Date
				+ "\t还车时间:" + (Return_Date==null?"未还":Return_Date) + "\t租金:" + (Payment==null?"未结算":Payment);
	}
	public String toStringd() {
		return "用户名:" + User_name + "     \t车已删除/车辆id:" + Car_id + "\t租车开始时间:" + Start_Date
				+ "\t还车时间:" + (Return_Date==null?"未还":Return_Date) + "\t租金:" + (Payment==null?"未结算":Payment);
	}
	public Record(Number id, Number user_id, Number car_id, Date start_Date, Date return_Date, Number payment) {
		super();
		this.id = id;
		User_id = user_id;
		Car_id = car_id;
		Start_Date = start_Date;
		Return_Date = return_Date;
		Payment = payment;
	}
	public Record(){}
}
