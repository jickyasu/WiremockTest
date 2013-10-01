package com.hooklogic;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HooklogicMain {
	private String winningTravelAdsUrlPrefix = "http://travelads.hlserve.com/Hotels/TravelAdsService";

	public String getTravelAds() throws HooklogicException {
		String hlFullUrl = winningTravelAdsUrlPrefix + "/WinningTravelAds"
				+ "?domain=www.hotels.com" + "&culture=en_US" + "&currency=GBP"
				+ "&destRegion=3D931ACE-E3FE-46B4-A243-61D44A22053B"
				+ "&checkIn=2013-10-16" + "&checkOut=2013-10-17" + "&rooms=1"
				+ "&adults=2" + "&children=0" + "&userAuthenticated=false"
				+ "&userIP=127.0.0.1" + "&userAgent=Mozillav"
				+ "&hlUserData=DX-Lqi9WiZlSeb80sMGK4uQ0z.SHA&maxRequested=15"
				+ "&userGuid=365cd652-79e8-4970-b823-b6b87d5e93b4";

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(hlFullUrl);
		String responseStr = "";

		try {
			HttpResponse testResponse = httpclient.execute(httpGet);
			responseStr = EntityUtils.toString(testResponse.getEntity());
		} catch (Exception e) {
			throw new HooklogicException(e);
		} finally {
			httpGet.releaseConnection();
		}

		return responseStr;
	}

	public void setWinningTravelAdsUrlPrefix(String winningTravelAdsUrl) {
		this.winningTravelAdsUrlPrefix = winningTravelAdsUrl;
	}

	public static void main(String args[]) throws Exception {
		HooklogicMain hlMain = new HooklogicMain();
		System.out.println(hlMain.getTravelAds());
	}
}
