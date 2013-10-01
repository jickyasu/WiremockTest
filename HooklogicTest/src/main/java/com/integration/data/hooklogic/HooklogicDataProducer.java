package com.integration.data.hooklogic;

import java.util.List;

import com.integration.data.AbstractDataProducer;

public class HooklogicDataProducer extends AbstractDataProducer {

	private List<String> hotelIds;
	
	public HooklogicDataProducer(List<String> hotelIds)
	{
		this.hotelIds = hotelIds;
	}
	
	public String generateResponse() throws Exception{
		
		String wTadPrefix = readFile("winningTravelAdsPrefix.txt");
		String wTadSuffix = readFile("winningTravelAdsSuffix.txt");
		String wTadEntry = generateEntries();
		
		return wTadPrefix + wTadEntry + wTadSuffix;
	}
	
	private String generateEntries() throws Exception
	{
		StringBuilder entries = new StringBuilder();
		
		for(String hId : hotelIds)
		{
			entries.append(getTadEntry(hId));
		}
		
		return entries.toString();
	}
	
	private String getTadEntry(String hotelId) throws Exception
	{
		String wTadEntry = readFile("winningTravelAdsTemplate.txt");
		return wTadEntry.replace("${hotelId}", hotelId);
	}

}
