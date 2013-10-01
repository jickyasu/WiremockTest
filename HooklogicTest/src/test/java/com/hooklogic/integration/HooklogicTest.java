package com.hooklogic.integration;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.UrlMatchingStrategy;
import com.github.tomakehurst.wiremock.http.Fault;
import com.hooklogic.HooklogicException;
import com.hooklogic.HooklogicMain;
import com.integration.data.hooklogic.HooklogicDataProducer;

import junit.framework.TestCase;

public class HooklogicTest extends TestCase
{
	protected static WireMockServer wireMockServer;
	private HooklogicMain hlMain;
	UrlMatchingStrategy mStrategy;
	
	@Before
	public void setUp() throws Exception
	{
		hlMain = new HooklogicMain();
		hlMain.setWinningTravelAdsUrlPrefix("http://localhost:8080");
		
		mStrategy = new UrlMatchingStrategy();
		mStrategy.setUrlPattern("/WinningTravelAds.*");
		
		wireMockServer = new WireMockServer(wireMockConfig().port(8080));
		wireMockServer.start();
	}
	
	@Test
	public void testGetTravelAds() throws Exception
    {	
		List<String> hotelIds = new ArrayList<String>();
		hotelIds.add("1");
		hotelIds.add("2");
		hotelIds.add("3");
		
		HooklogicDataProducer dp = new HooklogicDataProducer(hotelIds);
		String response = dp.generateResponse();
		
		stubFor(get(mStrategy)
			.willReturn(aResponse()
	            .withStatus(200)
	            .withHeader("Content-Type", "text/xml")
	            .withBody(response)));
		
		String actualResponse = hlMain.getTravelAds();
		
		assertEquals(response, actualResponse);
    }
	
	@Test(expected = HooklogicException.class)
	public void testTravelAdNoResponse() throws Exception
	{
		stubFor(get(mStrategy)
		        .willReturn(aResponse().withFault(Fault.EMPTY_RESPONSE)));
		
		hlMain.getTravelAds();
	}
	
	@After
	public void tearDown()
	{
		wireMockServer.stop();
	}
	
}
