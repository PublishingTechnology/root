package org.admin.gui.tapestry.pages;

import java.util.Date;

import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.apache.tapestry5.corelib.components.*;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.alerts.AlertManager;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.projectx.domain.types.ViewCI;

/**
 * Start page of application admin-gui-tapestry.
 */
public class Index
{
    @Property
    @Inject
    @Symbol(SymbolConstants.TAPESTRY_VERSION)
    private String tapestryVersion;


    @Component(id = "loadViewForm")
    private Form form;
    
    @Component(id = "reqViewId")
    private TextField reqViewIdField;
    
    @Property
    private String reqViewId;
        
    @Property
    private String viewName;
    public String getViewNameOut() { return viewName; };
    
    @Property
    private String viewId;
    public String getViewIdOut() { return viewId; };
    
    @InjectComponent
    private Zone viewForm;
 
    @Inject
    private Request request;
    

    Object onSuccess() {
		
		// make the Request to the content-srvc
		String domainCIType = "viewcis";
		String contentSrvcUrl = "http://localhost:8080/content-srvc/";
		String srvcCall = contentSrvcUrl + domainCIType + "/" + reqViewId;
		
		System.out.println("\n\nSrvc Call: " + srvcCall);
			
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
		
			this.viewId = reqViewId;
    	
        return request.isXHR() ? viewForm.getBody() : null;
    }

    Object onFailure() {
        return request.isXHR() ? viewForm.getBody() : null;
    }
        
    public Date getCurrentTime()
    {
        return new Date();
    }

    void onValidateFromLoadViewForm()
    {
        if (reqViewId == null || reqViewId.trim().equals(""))
        	form.recordError(reqViewIdField, "You must supply a View Id.");
    }
    
}
