package com.qed.demo6;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Customer {
    @XStreamAsAttribute
    private long customerId;
    // Personal name
    private String firstName;
    // Family name
    private String lastName;
    private List<String> middleNames;


}
