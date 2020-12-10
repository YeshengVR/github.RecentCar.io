package com.rentCar.Common.entry;

/**
 * 汽车表
 * 
 * @author HPK
 *
 */
public class Car {

	private Number id;// 车子id 11个字节
	private String Car_number; // 车牌号 10个字节
	private String brand_name;
	private Number Brand_id; // 车的类型id例如：奔驰 11个字节
	private String model; // 车的型号 40个字节
	private String color; // 车的颜色 20个字节
	private String category_name;
	private Number Category_id; // 车子的类型例如：紧凑型 11个字节
	private String T_Comments; // 手动档/自动档以及排量信息 100个字节
	private Number Price; // 车的价格 11个字节，整数9位，小数两位
	private Number Rent; // 租赁的价格 9个字节，整数有7位，小数两位
	private Number Status; // 租车的状态 0：可租，1：已被租
	private Number Useable;// 车子的状态 0：良好可用，1：车辆受损，无法使用，等待工作人员来维修

	public Car(String car_number, Number brand_id, String model, String color, Number category_id, String t_Comments,
			Number price, Number rent, Number status, Number useable) {
		super();
		Car_number = car_number;
		Brand_id = brand_id;
		this.model = model;
		this.color = color;
		Category_id = category_id;
		T_Comments = t_Comments;
		Price = price;
		Rent = rent;
		Status = status;
		Useable = useable;
	}

	public String toStrings() {
		return "车牌号：" + Car_number + "  \t 品牌：" + (brand_name == null ? "无品牌" : brand_name) + "         \t型号：" + (model == null ? "无型号" : model) + "     \t颜色：" + (color == null ? "无颜色" : color)
				+ "      \t类型：" + (category_name == null ? "无类型" : category_name) + "      \t 信息：" + (T_Comments == null ? "无信息" : T_Comments) + "  \t价格：" + Price + "  \t 租价：" + Rent;
	}

	public String toStringa() {
		return "车牌号：" + Car_number + "\t 品牌：" + (brand_name == null ? "无品牌" : brand_name) + "    \t型号："
				+ (model == null ? "无型号" : model) + "      \t颜色：" + (color == null ? "无颜色" : color) + "      \t 类型："
				+ (category_name == null ? "无类型" : category_name) + "    \t 信息："
				+ (T_Comments == null ? "无信息" : T_Comments) + "\t 价格：" + Price + "\t 租价：" + Rent + "\t\t 车辆状态："
				+ (Status.intValue() == 0 ? "未损坏" : "已损坏") + "\t 使用状态：" + (Useable.intValue() == 0 ? "未使用" : "已使用");
	}

	public Car(String car_number, String brand_name, String model, String color, String category_name,
			String t_Comments, Number price, Number rent, Number status, Number useable) {
		super();
		Car_number = car_number;
		this.brand_name = brand_name;
		this.model = model;
		this.color = color;
		this.category_name = category_name;
		T_Comments = t_Comments;
		Price = price;
		Rent = rent;
		Status = status;
		Useable = useable;
	}

	public Car(Number id, String car_number, String brand_name, String model, String color, String category_name,
			String t_Comments, Number price, Number rent, Number status, Number useable) {
		super();
		this.id = id;
		Car_number = car_number;
		this.brand_name = brand_name;
		this.model = model;
		this.color = color;
		this.category_name = category_name;
		T_Comments = t_Comments;
		Price = price;
		Rent = rent;
		Status = status;
		Useable = useable;
	}

	public void setBrand_id(String brand_name) {
		this.brand_name = brand_name;
	}

	public void setCategory_id(String category_id) {
		this.category_name = category_id;
	}

	public String getCar_number() {
		return Car_number;
	}

	public void setCar_number(String car_number) {
		Car_number = car_number;
	}

	public Number getBrand_id() {
		return Brand_id;
	}

	public void setBrand_id(Number brand_id) {
		Brand_id = brand_id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Number getCategory_id() {
		return Category_id;
	}

	public void setCategory_id(Number category_id) {
		Category_id = category_id;
	}

	public String getT_Comments() {
		return T_Comments;
	}

	public void setT_Comments(String t_Comments) {
		T_Comments = t_Comments;
	}

	public Number getPrice() {
		return Price;
	}

	public void setPrice(Number price) {
		Price = price;
	}

	public Number getRent() {
		return Rent;
	}

	public void setRent(Number rent) {
		Rent = rent;
	}

	public Number getStatus() {
		return Status;
	}

	public void setStatus(Number status) {
		Status = status;
	}

	public Number getUseable() {
		return Useable;
	}

	public void setUseable(Number useable) {
		Useable = useable;
	}

	public Number getId() {
		return id;
	}

	public void setId(Number id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "T_Car [Car_number=" + Car_number + ", Brand_id=" + Brand_id + ", model=" + model + ", color=" + color
				+ ", Category_id=" + Category_id + ", T_Comments=" + T_Comments + ", Price=" + Price + ", Rent=" + Rent
				+ ", Status=" + Status + ", Useable=" + Useable + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Brand_id == null) ? 0 : Brand_id.hashCode());
		result = prime * result + ((Car_number == null) ? 0 : Car_number.hashCode());
		result = prime * result + ((Category_id == null) ? 0 : Category_id.hashCode());
		result = prime * result + ((Price == null) ? 0 : Price.hashCode());
		result = prime * result + ((Rent == null) ? 0 : Rent.hashCode());
		result = prime * result + ((Status == null) ? 0 : Status.hashCode());
		result = prime * result + ((T_Comments == null) ? 0 : T_Comments.hashCode());
		result = prime * result + ((Useable == null) ? 0 : Useable.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (Brand_id == null) {
			if (other.Brand_id != null)
				return false;
		} else if (!Brand_id.equals(other.Brand_id))
			return false;
		if (Car_number == null) {
			if (other.Car_number != null)
				return false;
		} else if (!Car_number.equals(other.Car_number))
			return false;
		if (Category_id == null) {
			if (other.Category_id != null)
				return false;
		} else if (!Category_id.equals(other.Category_id))
			return false;
		if (Price == null) {
			if (other.Price != null)
				return false;
		} else if (!Price.equals(other.Price))
			return false;
		if (Rent == null) {
			if (other.Rent != null)
				return false;
		} else if (!Rent.equals(other.Rent))
			return false;
		if (Status == null) {
			if (other.Status != null)
				return false;
		} else if (!Status.equals(other.Status))
			return false;
		if (T_Comments == null) {
			if (other.T_Comments != null)
				return false;
		} else if (!T_Comments.equals(other.T_Comments))
			return false;
		if (Useable == null) {
			if (other.Useable != null)
				return false;
		} else if (!Useable.equals(other.Useable))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		return true;
	}

	public Car(Number id, String car_number, Number brand_id, String model, String color, Number category_id,
			String t_Comments, Number price, Number rent, Number status, Number useable) {
		this.id = id;
		this.Car_number = car_number;
		this.Brand_id = brand_id;
		this.model = model;
		this.color = color;
		this.Category_id = category_id;
		this.T_Comments = t_Comments;
		this.Price = price;
		this.Rent = rent;
		this.Status = status;
		this.Useable = useable;
	}

	public Car() {
	}

}
