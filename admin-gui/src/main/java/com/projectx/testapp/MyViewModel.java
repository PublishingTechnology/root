package com.projectx.testapp;

import java.io.IOException;
import java.net.URI;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.projectx.domain.types.ViewCI;

public class MyViewModel {

	private int count;

	@Init
	public void init() {
		count = 0;
	}

	@Command
	@NotifyChange("latestViewName")
	public void cmd() {
		++count;
	}

	public int getCount() {
		return count;
	}
	
	public String getLatestViewName() {
		// Request to the content-srvc for the current viewCI id
		String viewName = retrieveViewName();
		
		return viewName;
	}

	private String retrieveViewName() {
		
		String viewName = "Click Next to retrieve available view names";
		
		// make the Request to the content-srvc
		String domainCIType = "viewcis";
		String contentSrvcUrl = "http://localhost:8080/content-srvc/";
		String srvcCall = contentSrvcUrl + domainCIType + "/" + count;
		
		System.out.println("\n\nSrvc Call: " + srvcCall);
		
		if (count > 0) {
			
			RestTemplate restTemplate = new RestTemplate();
			
			try {
				
			ViewCI view = restTemplate.getForObject(srvcCall, ViewCI.class);
			//String view = restTemplate.getForObject(srvcCall, String.class);
			if (view != null)
				viewName = view.getViewName();
				//viewName = view;
			} catch (HttpClientErrorException hcee) {
				viewName = "No more viewNames to find";
			}
		}
		return viewName;
	}
}
