package com.jpmorgan.report.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.jpmorgan.report.helper.MessageProcessor;
import com.jpmorgan.report.helper.Validation;
import com.jpmorgan.report.helper.ValidationUtil;
import com.jpmorgan.report.helper.EveryDayTradeProcessor;
import com.jpmorgan.report.pojo.Entity;


public class ValidatingInterceptor implements InvocationHandler{
	
	private MessageProcessor messageProcessor;
	
	public ValidatingInterceptor(MessageProcessor messageProcessor){
		this.messageProcessor = messageProcessor;
	}

	  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		  if(method.isAnnotationPresent(Validation.class)){
		  ValidationUtil util = new ValidationUtil();
		  util.validate((Entity)args[0]);
		  }
         method.invoke(this.messageProcessor, args);
         
         EveryDayTradeProcessor everydayProcessor = EveryDayTradeProcessor.getInstance();
         
         everydayProcessor.process((Entity)args[0]);
         
         return new Object();
      }

//	public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
//		ValidationUtil utils = new ValidationUtil();
//		
//		System.out.println("Called Inceptor");
//		
//		arg3.
//		return null;
//	}

}
