package entity;

import java.io.*;

public class Response implements Serializable{
	private static final long serialVersionUID = 12071113985L;
	
	private RequestType type;//��Ӧ����
	private Serializable value;//��Ӧ��ֵ
	
	public Response(RequestType type){
		this.type=type;
	}
	public void setData(Serializable value){
		this.value=value;
	}
	public Serializable getData(){
		return value;
	}
	public RequestType getType(){
		return type;
	}
}

