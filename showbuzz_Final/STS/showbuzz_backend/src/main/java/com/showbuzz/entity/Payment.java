package com.showbuzz.entity;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="payment")
public class Payment 
{
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name="payment_id")
	private int id;
	private String razorpay_payment_id;
	@Column(name="order_id")
	private String orderId;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;
	private String signature;
	@Column(name="totalAmount")
	private float totalAmount;
	@CreationTimestamp
	private Date payment_timestamp;
	@OneToMany(mappedBy = "payment" ,fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	private List<Orders> orderList;
	
	
	public Payment() {
		super();
		orderList = new ArrayList<>();
	}
	
	
	public Payment(int id, String razorpay_payment_id, String orderId, User userId, String signature, float totalAmount,
			Date payment_timestamp) {
		super();
		this.id = id;
		this.razorpay_payment_id = razorpay_payment_id;
		this.orderId = orderId;
		this.user = userId;
		this.signature = signature;
		this.totalAmount = totalAmount;
		this.payment_timestamp = payment_timestamp;
	}
	
	
	
public Payment(int id) {
		this.id = id;
	}
//	public Payment(int id, String razorpay_payment_id, String orderId, User userId, String signature,
//			float totalAmount) {
//		super();
//		this.id = id;
//		this.razorpay_payment_id = razorpay_payment_id;
//		this.orderId = orderId;
//		this.user_id = userId;
//		this.signature = signature;
//		this.totalAmount = totalAmount;
//	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRazorpay_payment_id() {
		return razorpay_payment_id;
	}
	public void setRazorpay_payment_id(String razorpay_payment_id) {
		this.razorpay_payment_id = razorpay_payment_id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Date getPayment_timestamp() {
		return payment_timestamp;
	}
	public void setPayment_timestamp(Date payment_timestamp) {
		this.payment_timestamp = payment_timestamp;
	}
	
	
	
	public List<Orders> getOrderList() {
		return orderList;
	}


	public void setOrderList(List<Orders> orderList) {
		this.orderList = orderList;
	}
	
	public void addOrder(Orders order) {
		this.orderList.add(order);
		order.setPayment(this);
	}


	@Override
	public String toString() {
		return "Payment [id=" + id + ", razorpay_payment_id=" + razorpay_payment_id + ", orderId=" + orderId
				+ ", userId=" + user + ", signature=" + signature + ", totalAmount=" + totalAmount
				+ ", payment_timestamp=" + payment_timestamp + "]";
	}
	
	
	
	
}
//payment_id         
//razorpay_payment_id
//order_id           
//signature          
//user_id            
//totalAmount        
//payment_timestamp  