package com.qed.demo6;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@XStreamAlias("order")
public class Order {
    @XStreamAsAttribute
    private long orderId;
    private Customer customer;
    private Address billTo;
    private Shipping shipping;
    private Address shipTo;
    @XStreamAsAttribute
	private Float total;


}
