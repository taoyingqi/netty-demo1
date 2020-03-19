package com.qed.demo2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class SubscribeResp implements Serializable {

    private static final long serialVersionUID = 1L;

    private int subReqID;
    private int respCode;
    private String desc;

}
