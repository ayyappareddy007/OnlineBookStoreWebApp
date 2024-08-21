package com.obs.model;

public class OrderModel extends BookModel {
	private int od_id;
	private int o_id;
	private int u_id;
	private String date;
	private String status;
	private int quantity;
	
	public OrderModel() {	}

	public int getOd_id() {
		return od_id;
	}

	public void setOd_id(int od_id) {
		this.od_id = od_id;
	}

	public int getO_id() {
		return o_id;
	}

	public void setO_id(int o_id) {
		this.o_id = o_id;
	}

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderModel [od_id=" + od_id + ", o_id=" + o_id + ", u_id=" + u_id + ", date=" + date + ", status="
				+ status + ", quantity=" + quantity + "]";
	}

	
	
	
	
	
	
}
