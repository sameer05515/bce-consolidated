package com.p.service.export.commons;

import java.io.IOException;

import com.p.service.exception.RestServiceException;

public interface Doer {
	
	public void create(String filePath) throws RestServiceException, IOException;
	
	public void consume(String filePath) throws RestServiceException, IOException;

}
