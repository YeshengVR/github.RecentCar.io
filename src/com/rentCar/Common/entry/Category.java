package com.rentCar.Common.entry;

	/**
	 * 汽车类型表
	 * @author HPK
	 *
	 */
public class Category {
	
	private Number id;//汽车类型id 11位
	private String name;//汽车类型名  例如：舒适性，紧凑型
	public Number getId() {
		return id;
	}
	public void setId(Number id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Category other = (Category) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "T_Category [id=" + id + ", name=" + name + "]";
	}
	public Category(Number id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Category(){}
}
