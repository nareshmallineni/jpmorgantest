package com.jpmorgan.report.processor;

import java.lang.reflect.Proxy;

import com.jpmorgan.report.helper.MessageProcessor;
import com.jpmorgan.report.interceptor.ValidatingInterceptor;

public class ProcessorFactory {
	public Processor getInstance(){
		 return (Processor) Proxy.newProxyInstance(
	             this.getClass().getClassLoader(),
	             new Class[]{Processor.class},
	             new ValidatingInterceptor(new MessageProcessor()));
		}
}
