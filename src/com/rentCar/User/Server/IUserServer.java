package com.rentCar.User.Server;

import java.util.ArrayList;

import com.rentCar.User.entry.User;

public interface IUserServer {
	
	/**
	 * 用户登录的方法
	 * @return	返回登陆的用户
	 */
	public User login(String username,String password);
	
	/**
	 * 用户注册的方法
	 * @return 返回一个判断用户是否注册成功的true/false
	 */
	public boolean register(User user);

	/**
	 * 用户查看所有汽车的方法，只显示可以使用的汽车，汽车是坏的的话就不显示
	 * @return	返回一个查找到的全部的汽车的集合
	 */
	public <T> ArrayList<T> selectCar();

	/**
	 * 用户根据每天的租金价格升序来查询汽车的方法
	 * @return		返回一个根据需求所找出的汽车集合
	 */
	public <T> ArrayList<T> selectCarByT_priceasc();
	
	/**
	 * 用户根据每天的租金价格降序来查询汽车的方法
	 * @return		返回一个更具需求所找出的汽车集合
	 */
	public <T> ArrayList<T> selectCarByT_pricedesc();
	
	/**
	 * 用户根据品牌来查询汽车的方法
	 * @param sql	传递一个需求的sql语句
	 * @return		返回一个根据需求所找出的汽车集合
	 */
	public <T> ArrayList<T> selectCarByT_Brand(String brand);
	
	/**
	 * 用户根据类别来查询汽车的方法
	 * @param sql	传递一个需求的sql语句
	 * @return		返回一个根据需求所找出的汽车集合
	 */
	public <T> ArrayList<T> selectCarByT_Category(String category);

	/**
	 * 用户租车的方法
	 * @param user  传递一个要租车的用户信息
	 * @param carNumber 传递一个车牌号
	 * @return		返回一个租车是否成功的布尔值
	 */
	public boolean Rent_Car(User user,String carNumber);

	
	/**
	 * 用户还车的方法
	 * @param user  传递一个需要还车的用户信息
	 * @param carNumber 传递一个车牌号
	 * @return		返回一个还车是否成功的布尔值
	 */
	public boolean Return_Car(User user,String carNumber);

	/**
	 * 查看本用户的所有租赁记录
	 * @param user	传递本User对象的用户信息
	 * @return		返回一个本用户的所有租赁信息的集合
	 */
	public <T> ArrayList<T> selectRecord(User user);

}
