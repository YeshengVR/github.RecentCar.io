package com.rentCar.Common.Methods;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.rentCar.Common.entry.Car;
import com.rentCar.Util.JDBCUtil;

public class RentCar {
	// 返回查询的数据并打印，并传回查询到的表数据
		public static <T_car> ArrayList<Car> printSelectmoreCar(ResultSet rs) {
			ArrayList<Car> arr = JDBCUtil.Select(rs, Car.class);
		for (Car t_Car : arr) {
			System.out.println(t_Car.toStrings());
			}
		return arr;
			}
}
