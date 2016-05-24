package entity;

import java.io.Serializable;
import java.util.Properties;


public class Request implements Serializable{
	private static final long serialVersionUID = 120791203985L;
	
	private RequestType type;//请求类型
	private Properties parameters;//请求所带的参数
	
	public Request(RequestType type){
		parameters=new Properties();
		this.type=type;
	}
	public void setData(String key,String value){
		this.parameters.setProperty(key,value);
	}
	public String getData(String key){
		return this.parameters.getProperty(key);
	}
	public Properties getAllParameters(){
		return this.parameters;
	}
	public RequestType getType(){
		return type;
	}

}

