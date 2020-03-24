package com.qed.demo6;


public class OrderFactory {

    public static Order create(long orderId) {
        Address address = new Address() {{
            setCountry("中国");
            setState("江苏省");
            setCity("南京市");
            setStreet1("龙眠大道");
            setPostCode("123321");
        }};

        Customer customer = new Customer() {{
            setCustomerId(orderId);
            setFirstName("李");
            setLastName("林峰");
        }};

        Order order = new Order() {{
            setOrderId(orderId);
            setTotal(9999.999f);
            setBillTo(address);
            setCustomer(customer);
            setShipping(Shipping.INTERNATIONAL_MAIL);
            setShipTo(address);
        }};
        return order;
    }


}
