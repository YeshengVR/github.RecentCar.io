package com.rentCar.User.Dao;

import java.util.ArrayList;

import com.rentCar.User.entry.User;


/**
 * User的接口方法
 * @author HPK
 *
 */
public interface IUser {
	
	/**
	 * 用户的登录方法
	 * @param id 用户的id账号
	 * @param password 用户的账号密码
	 * @return	返回登陆的对象
	 */
	public User login(String id,String password);
	
	/**
	 * 用户注册的方法
	 * @return 返回一个判断用户是否注册成功的true/false
	 */
	public boolean register(User user);
	
	/**
	 * 用户查看所有汽车的方法，只显示可以使用的汽车，汽车是坏的的话就不显示
	 * @return	返回一个查找到的全部的汽车的集合
	 */
	public <T> ArrayList<T> selectCar(String sql);
	
	/**
	 * 用户租车的方法
	 * @param user  传递一个要租车的用户信息
	 * @param carNumber	传递一个要使用的车的id信息
	 * @param useable 使用状态
	 * @return		返回一个租车是否成功的布尔值
	 */
	boolean RCarUpcar(User user, String carNumber, int useable);
	
	/**
	 * 用户租车改变表的方法
	 * @param user		传递一个登录的用户
	 * @param carNumber	传递一个车的id
	 */
	public boolean rentCarUpRecord(User user, String carNumber,String sql);
	
	/**
	 * 用户租车查找用户id的方法
	 * @param user	传递一个登陆的user用户
	 * @return		查找用户在数据库的id的方法
	 */
	public Number Rselectid(User user);
	
	/**
	 * 查看本用户的所有租赁记录
	 * @param user	传递本User对象的用户信息
	 * @return		返回一个本用户的所有租赁信息的集合
	 */
	public <T> ArrayList<T> selectRecord(String sql);









}
