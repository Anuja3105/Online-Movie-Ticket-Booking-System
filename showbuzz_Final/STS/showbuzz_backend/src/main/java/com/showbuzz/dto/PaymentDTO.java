package com.showbuzz.dto;

import java.util.Date;

public class PaymentDTO 
{
	private int paymentId;
	private String razorpayPaymentId;
	private String orderId;
	private String signature;
	private int userId;
	private float totalAmount;
	private Date paymentTimestamp;
	
	public PaymentDTO() {
	}

	public PaymentDTO(int paymentId, String razorpayPaymentId, String orderId, String signature, int userId,
			float totalAmount, Date paymentTimestamp) {
		super();
		this.paymentId = paymentId;
		this.razorpayPaymentId = razorpayPaymentId;
		this.orderId = orderId;
		this.signature = signature;
		this.userId = userId;
		this.totalAmount = totalAmount;
		this.paymentTimestamp = paymentTimestamp;
	}
	
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public String getRazorpayPaymentId() {
		return razorpayPaymentId;
	}
	public void setRazorpayPaymentId(String razorpayPaymentId) {
		this.razorpayPaymentId = razorpayPaymentId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Date getPaymentTimestamp() {
		return paymentTimestamp;
	}
	public void setPaymentTimestamp(Date paymentTimestamp) {
		this.paymentTimestamp = paymentTimestamp;
	}
	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", razorpayPaymentId=" + razorpayPaymentId + ", orderId=" + orderId
				+ ", signature=" + signature + ", userId=" + userId + ", totalAmount=" + totalAmount
				+ ", paymentTimestamp=" + paymentTimestamp + "]";
	}
	
	

}
//payment_id         
//razorpay_payment_id
//order_id           
//signature          
//user_id            
//totalAmount        
//payment_timestamp 