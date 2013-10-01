package com.integration.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;

public abstract class AbstractDataProducer 
{
	public abstract String generateResponse() throws Exception;
	
	public String readFile(String filename) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader fileBufferedReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(filename)));
        while ((line = fileBufferedReader.readLine()) != null) {
            sb.append(line);
        }
        IOUtils.closeQuietly(fileBufferedReader);
        return sb.toString();
    }
	
}
