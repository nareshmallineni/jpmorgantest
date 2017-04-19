package com.jpmorgan.report.processor;

import com.jpmorgan.report.helper.Validation;
import com.jpmorgan.report.pojo.Entity;

public interface Processor {
	@Validation
    void process(Entity entity);
	
	static Processor newProcessor() {
        return new ProcessorFactory().getInstance();
    }
}
