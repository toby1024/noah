package cn.skio.gateway.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ResultMessage<T> implements Serializable{

    private int code;
    private String message;
    private T resultEntity;

    public ResultMessage(int code, String message){
        this.code = code;
        this.message = message;
    }
}
