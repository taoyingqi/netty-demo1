package com.qed.demo6;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@XStreamAlias("billTo")
public class Address {
    private String country;
    private String state;
    private String city;
    // First line of street information (required)
    private String street1;
    // Second line of street information (required)
    private String street2;
    private String postCode;

}
