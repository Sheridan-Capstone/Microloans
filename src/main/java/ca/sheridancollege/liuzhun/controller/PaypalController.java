package ca.sheridancollege.liuzhun.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.http.HttpResponse;
import com.paypal.orders.AmountWithBreakdown;
import com.paypal.orders.ApplicationContext;
import com.paypal.orders.Order;
import com.paypal.orders.OrderRequest;
import com.paypal.orders.OrdersCaptureRequest;
import com.paypal.orders.OrdersCreateRequest;
import com.paypal.orders.PurchaseUnitRequest;

import ca.sheridancollege.liuzhun.paypal.PayPalClient;

@RestController
public class PaypalController {
	
	PayPalClient pcc = new PayPalClient();
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, value = "/demo/checkout/api/paypal/order/create/")
	public String createOrder(@RequestBody Map<String, Integer> amountObj) throws IOException, Exception {
		
		int amount = amountObj.get("amount");
		
		if(amount <= 0) {
			return "";
		}
				
		OrdersCreateRequest request = new OrdersCreateRequest();
	    request.prefer("return=representation");
	    request.requestBody(buildRequestBody(String.valueOf(amount)));
	    
	    HttpResponse<Order> res = pcc.client().execute(request);
	   	    
	    return res.result().id();
	}
	
	@PostMapping(consumes = {MediaType.TEXT_PLAIN_VALUE}, value = "/demo/checkout/api/paypal/order/capture/")
	public Order captureOrder (@RequestBody String orderID) throws IOException {
		
		OrdersCaptureRequest request = new OrdersCaptureRequest(orderID);
	    request.requestBody(new OrderRequest());
	    
	    HttpResponse<Order> res = pcc.client().execute(request);
	    
	    System.out.println("Status Code: " + res.statusCode());
	    System.out.println("Status: " + res.result().status());
	    
	    return res.result();
	}
	
	private OrderRequest buildRequestBody(String amount) {
		OrderRequest or = new OrderRequest();
		or.checkoutPaymentIntent("CAPTURE");
		
		List<PurchaseUnitRequest> purchaseUnitRequests = new ArrayList<PurchaseUnitRequest>();
		
		PurchaseUnitRequest purchaseUnitRequest = 
				new PurchaseUnitRequest().referenceId("PUHF")
				.description("testing the button")
				.amountWithBreakdown(new AmountWithBreakdown().currencyCode("CAD").value(amount));
		
		purchaseUnitRequests.add(purchaseUnitRequest);
		or.purchaseUnits(purchaseUnitRequests);
		
		return or;
	}
	
}
