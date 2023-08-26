package com.showbuzz.services;

import java.util.Collections;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.showbuzz.dao.IPaymentDao;
import com.showbuzz.dao.IUserDao;
import com.showbuzz.dto.DtoEntityConvertor;
import com.showbuzz.dto.PaymentDTO;
import com.showbuzz.entity.Payment;
import com.showbuzz.entity.User;


@Transactional
@Service
public class PaymentServiceImpl 
{
	@Autowired
	private IPaymentDao paymentDao;
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private DtoEntityConvertor convertor;
	
	public Map<String, Object> addPayment(PaymentDTO dto)
	{
		Payment payment=convertor.DtoToEntity(dto);
		User user = userDao.findById(dto.getUserId());
		user.addUserPayment(payment);
		payment=paymentDao.save(payment);
		return Collections.singletonMap("payment_timeStamp", payment.getPayment_timestamp());
	}
	
	public PaymentDTO findPaymentByOrder_id(String orderId)
	{
		Payment payment=paymentDao.findPaymentByOrderId(orderId);
		return convertor.EntityToDto(payment);
	}
	
	public PaymentDTO findByPayment_id(int id)
	{
		Payment payment=paymentDao.findById(id);
		return convertor.EntityToDto(payment);
	}

	public int findByString(String id) {
		
		return paymentDao.findIdByRazorpayId(id);
	}
}
