package com.showbuzz.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.showbuzz.dto.PaymentDTO;
import com.showbuzz.dto.Response;
import com.showbuzz.dto.TheatreDTO;
import com.showbuzz.services.PaymentServiceImpl;
import com.showbuzz.services.SeatBookingServiceImpl;
import com.showbuzz.services.TheatreServiceImpl;

@CrossOrigin(origins = "*")
@RestController
public class PaymentControllerImpl
{
	@Autowired
	PaymentServiceImpl paymentService;
	
	@Autowired
	SeatBookingServiceImpl bookingService;
	@Autowired
	TheatreServiceImpl theatreService;
	
	@RequestMapping("/paymentsucess")
	public ResponseEntity<?> PaymentSucess(@RequestBody Map<String,Object> data,PaymentDTO dto)
	{
		dto.setOrderId(data.get("order_id").toString());
		dto.setRazorpayPaymentId(data.get("payment_id").toString());
		dto.setSignature(data.get("signature").toString());
		dto.setTotalAmount(Float.parseFloat(data.get("amount").toString()));
		dto.setUserId(Integer.parseInt(data.get("userId").toString()));
		Map<String,Object>result = paymentService.addPayment(dto);

		return Response.success(result);
	}
	@CrossOrigin
	@PostMapping("/payment/createOrder")
	@ResponseBody
	public String create_order(@RequestBody Map<String, Object> data,	HttpSession session) throws RazorpayException {
		float amount = Float.parseFloat(data.get("amount").toString());
		System.out.println("Theatre id : " + data.get("theatre_id").toString());
		int theatre_id=Integer.parseInt(data.get("theatre_id").toString());
		System.out.println(theatre_id);
		System.out.println(amount);
		TheatreDTO theatre=theatreService.findById(theatre_id);
		System.out.println(theatre.toString());
		String key1=theatre.getKey1();
		String key2=theatre.getKey2();
		var client = new RazorpayClient(key1,key2);
		JSONObject obj = new JSONObject();
		obj.put("amount", amount*100);
		obj.put("currency", "INR");
		obj.put("receipt", "txn_123456");
		Order order=client.Orders.create(obj);
		String orders=order.toString();
		System.out.println(order);
		

		return orders;
	}
	
	@GetMapping("/payment/id/{id}")
	public ResponseEntity<?> findByRazorPayId(@PathVariable(name="id") String id){
		int payment_id = paymentService.findByString(id);
		return Response.success(payment_id);
	}
}
