package com.example.interact;

import javax.servlet.annotation.WebServlet;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.projectx.domain.types.ViewCI;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("interact")
public class InteractUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = InteractUI.class)
	public static class Servlet extends VaadinServlet {
	}

	VerticalLayout rootLayout = new VerticalLayout();
	HorizontalLayout selectViewIDLayout = new HorizontalLayout();
	
	
	@Override
	protected void init(VaadinRequest request) {
		
		rootLayout.setMargin(true);
		rootLayout.addComponent(selectViewIDLayout);

		selectViewIDLayout.setCaption("Select View");
		selectViewIDLayout.setMargin(true);
		
		final TextField reqViewId = new TextField();
		final Button loadBttn = new Button();
		loadBttn.setCaption("Load View Id");

		selectViewIDLayout.addComponent(reqViewId);
		selectViewIDLayout.addComponent(loadBttn);
		
		final VerticalLayout viewDetailsLayout = new VerticalLayout();
		viewDetailsLayout.setMargin(true);
		viewDetailsLayout.setCaption("View Details");
		final Label viewNameLab = new Label();
		final Label viewIdLab = new Label();
		viewDetailsLayout.addComponent(viewNameLab);
		viewDetailsLayout.addComponent(viewIdLab);		
		
		loadBttn.addClickListener(new ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				
				String viewName = "View Id not found";
				String viewId = reqViewId.getValue();
				
				// make the Request to the content-srvc
				String domainCIType = "viewcis";
				String contentSrvcUrl = "http://localhost:8080/content-srvc/";
				String srvcCall = contentSrvcUrl + domainCIType + "/" + viewId;
				
				System.out.println("\n\nSrvc Call: " + srvcCall);
				
				if (viewId != null && !viewId.equals("")) {
					
					RestTemplate restTemplate = new RestTemplate();
					
					try {
						
						ViewCI view = restTemplate.getForObject(srvcCall, ViewCI.class);
						//String view = restTemplate.getForObject(srvcCall, String.class);
						if (view != null) {
							viewNameLab.setCaption("View Name: " + view.getViewName());
							viewIdLab.setCaption("View Id: " + Long.toString(view.getId()));
						}
					} catch (RuntimeException re) {
						viewNameLab.setCaption("Could not retrieve View by Id: " + reqViewId.getValue());
						viewIdLab.setCaption(" --- ");
					}
				} else {
					reqViewId.setValue("You must supply a view Id");
					
				}
			}
		});
		
		rootLayout.addComponent(viewDetailsLayout);
		setContent(rootLayout);
	}

}