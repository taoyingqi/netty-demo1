package com.qed.demo9;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public final class NettyMessage {
    private Header header; //消息头
    private Object body; //消息体


}
