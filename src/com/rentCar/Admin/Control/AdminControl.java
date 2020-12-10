package com.rentCar.Admin.Control;

import java.util.ArrayList;

import com.rentCar.Admin.Server.impl.AdminSever;
import com.rentCar.Common.entry.Car;
import com.rentCar.Common.entry.Record;
import com.rentCar.User.entry.User;

public class AdminControl {

	private AdminSever as = new AdminSever(); 
	/**
	 * 管理员查看所有汽车的方法，显示全部汽车
	 * @return	返回一个查找到的全部的汽车的集合
	 */
	public ArrayList<Car> selectCar(){
		ArrayList<Car> selectCar = as.selectCar();
		return selectCar;
	}
	
	/**
	 * 根据车牌号查找指定汽车的方法
	 * @param idNumber  车牌号信息
	 * @return			返回一个指定汽车的对象
	 */
	public Car selectByidNumberCar(String idNumber){
		Car selectByidNumberCar = as.selectByidNumberCar(idNumber);
		return selectByidNumberCar;
	}
	
	/**
	 * 添加汽车的方法
	 * @param car	传递一个封装好的汽车对象
	 * @return		判断是否添加成功
	 */
	public boolean AddCar(Car car){
		boolean addCar = as.AddCar(car);
		return addCar;
	}
	/**
	 * 根据 品牌名查询对应的品牌id
	 * @param Brand		品牌名
	 * @return			返回一个品牌id
	 */
	public Number UpdateBrand(String Brand){
		Number num = as.UpdateBrand(Brand);
		return num;
	}
	
	/**
	 * 根据类型名查询对应的类型id
	 * @param category	类型名
	 * @return			返回一个类型id
	 */
	public Number UpdateCategory(String category){
		Number updateCategory = as.UpdateCategory(category);
		return updateCategory;
	}
	
	public boolean carNumbers(String carNumber){
		boolean carNumber2 = as.carNumber(carNumber);
		return carNumber2;
	}
	/**
	 * 修改汽车信息的方法
	 * @param car	如果车辆已被租赁，则不可修改，需要修改可用不可用。
	 * @return		判断是否修改成功
	 */
	public boolean UpdateCar(Car car){
		boolean updateCar = as.UpdateCar(car);
		return updateCar;
	}
	
	public boolean deleteCar(String id_Number){
		boolean deleteCar = as.deleteCar(id_Number);
		return deleteCar;
	}
	
	/**
	 * 查看所有用户全部租赁记录
	 * @return	全部用户的租赁记录集合
	 */
	public ArrayList<String> selectAllRecord(User user){
		ArrayList<String> selectAllRecord = as.selectAllRecord(user);
		return selectAllRecord;
	}
	
	/**
	 * 查看指定用户租赁记录
	 * @return	指定用户的全部租赁记录
	 */
	public ArrayList<Record> selectByUserIdRecord(String username){
		ArrayList<Record> selectByUserIdRecord = as.selectByUserIdRecord(username);
		return selectByUserIdRecord;
	}
	
	/**
	 * 查看指定汽车的租赁记录
	 * @return	指定汽车的全部租赁记录
	 */
	public ArrayList<Record> selectByIdNumberRecord(String idNumber){
		ArrayList<Record> selectByIdNumberRecord = as.selectByIdNumberRecord(idNumber);
		return selectByIdNumberRecord;
	}
	
	/**
	 * 查询指定用户信息
	 * @param username	查询的用户名
	 * @return			返回一个查询到的用户对象
	 */
	public User selectByUserid(String username){
		User selectByusernameTUser = as.selectByusernameTUser(username);
		return selectByusernameTUser;
	}
	
	/**
	 * 查询全部用户信息
	 * @return			返回一个全部用户信息群
	 */
	public ArrayList<User> selectAllUser(){
		ArrayList<User> selectAllTUser = as.selectAllTUser();
		return selectAllTUser;
	}
	
	/**
	 * 删除用户
	 */
	public boolean deleteUser(){
		
		return false;
	}
	/**
	 * 退出的方法
	 */
	public void Exitsever(){
		
	}
}
