package com.rentCar.Admin.Server;

import java.util.ArrayList;

import com.rentCar.Common.entry.Car;
import com.rentCar.User.entry.User;

public interface IAdminSever {

	/**
	 * 管理员查看所有汽车的方法，显示全部汽车
	 * @return	返回一个查找到的全部的汽车的集合
	 */
	public <T> ArrayList<T> selectCar();
	
	/**
	 * 根据车牌号查找指定汽车的方法
	 * @param idNumber  车牌号信息
	 * @return			返回一个指定汽车的对象
	 */
	public Car selectByidNumberCar(String idNumber);
	
	/**
	 * 添加汽车的方法
	 * @param car	传递一个封装好的汽车对象
	 * @return		判断是否添加成功
	 */
	public boolean AddCar(Car car);
	
	/**
	 * 修改汽车信息的方法
	 * @param car	如果车辆已被租赁，则不可修改，需要修改可用不可用。
	 * @return		判断是否修改成功
	 */
	public boolean UpdateCar(Car car);
	
	/**
	 * 查看所有用户全部租赁记录
	 * @return	全部用户的租赁记录集合
	 */
	public <T> ArrayList<T> selectAllRecord(User user);
	
	/**
	 * 查看指定用户租赁记录
	 * @return	指定用户的全部租赁记录
	 */
	public <T> ArrayList<T> selectByUserIdRecord(String username);
	
	/**
	 * 查看指定汽车的租赁记录
	 * @return	指定汽车的全部租赁记录
	 */
	public <T> ArrayList<T> selectByIdNumberRecord(String idNumber);
	
	/**
	 * 查询指定用户信息
	 * @return	返回一个装载了一个用户的集合
	 */
	public User selectByusernameTUser(String username);
	
	/**
	 * 查询全部用户信息
	 * @return	返回一个全部用户的集合
	 */
	public <T> ArrayList<T> selectAllTUser();
	
	/**
	 * 删除汽车的方法
	 * @param CarNumber	车牌号
	 * @return			返回一个是否删除成功的布尔值
	 */
	public boolean deleteCar(String CarNumber);
}
