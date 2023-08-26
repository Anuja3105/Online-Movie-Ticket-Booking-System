package com.showbuzz.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.showbuzz.entity.Payment;

public interface IPaymentDao extends JpaRepository<Payment, Integer>
{
	 Payment findById(int id);
	 Payment findPaymentByOrderId(String orderId);
	 @Query(value="select payment_id FROM payment WHERE payment.user_id=?;",nativeQuery=true)
	 List<Payment> findPaymentByUserId(int id);
	 Payment findPaymentBySignature(String signature);
	 @Query(value ="SELECT p.payment_id FROM payment p WHERE p.razorpay_payment_id = ?;",nativeQuery = true)
	 int  findIdByRazorpayId(String id);
	 @Query(value ="SELECT p.payment_id FROM payment p WHERE p.order_id = ?;",nativeQuery = true)
	 int findIdByOrderId(String order_id);
}
