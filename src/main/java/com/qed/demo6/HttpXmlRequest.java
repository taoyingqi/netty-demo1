package com.qed.demo6;

import io.netty.handler.codec.http.FullHttpRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class HttpXmlRequest {
    private FullHttpRequest request;
    private Object body;


}
