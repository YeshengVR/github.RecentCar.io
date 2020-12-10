package com.rentCar.Admin.Dao;

import java.util.ArrayList;

public interface IAdminDao {
	

	/**
	 * 根据需求查询汽车信息
	 * @param sql	需求的sql语句
	 * @return		返回一个需要查询到的汽车信息
	 */
	public <T> ArrayList<T> selectCar(String sql);
	
	/**
	 * 根据需求修改/添加汽车信息记录
	 * @param sql	需求的sql语句
	 * @return		返回一个是否修改成功的信息
	 */
	public boolean UpdateNothing(String sql);
	
	/**
	 * 根据需求查询租赁记录
	 * @param sql 查询筛选租赁记录的sql语句
	 * @return	返回一个需求的的租赁记录
	 */
	public <T> ArrayList<T> selectRecord(String sql);
	
	/**
	 * 查找用户信息的方法
	 * @param sql	通过sql语句来指定获取谁的信息或者哪一批的信息
	 * @return		返回一个装载了指定查找的所有用户的集合
	 */
	public <T> ArrayList<T> selectUser(String sql);
	
	/**
	 * 根据用户名查找用户id的方法
	 * @param username	用户名
	 * @return			用户id
	 */
	public Number Rselectid(String username);
}
