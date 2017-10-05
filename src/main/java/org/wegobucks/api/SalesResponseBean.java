package org.wegobucks.api;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SalesResponseBean {

	
	private List<OrderResponseBean> orderResponseBeans;

	@JsonProperty("orders")
	public List<OrderResponseBean> getOrderResponseBean() {
		return orderResponseBeans;
	}

	public void setOrderResponseBean(List<OrderResponseBean> orderResponseBean) {
		this.orderResponseBeans = orderResponseBean;
	}
	
	@JsonProperty("totalAmount")
	public double getTotalAmount() {
		BigDecimal totalAmount = new BigDecimal(0);
		for(OrderResponseBean bean: orderResponseBeans) {
			totalAmount = totalAmount.add(new BigDecimal(bean.getAmount()));
		}
		totalAmount.setScale(2, RoundingMode.HALF_EVEN);
		return totalAmount.doubleValue();
	}
}
