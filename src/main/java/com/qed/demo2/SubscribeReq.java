package com.qed.demo2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class SubscribeReq implements Serializable {

    private static final long serialVersionUID = 1L;

    private int subReqID;
    private String userName;
    private String productName;
    private String phoneNumber;
    private String address;


}
