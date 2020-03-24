package com.qed.demo6;

import io.netty.handler.codec.http.FullHttpResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class HttpXmlResponse {
    private FullHttpResponse response;
    private Object result;


}
