package org.rootbeer.rbms.view;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("rbms")
@SuppressWarnings("serial")
public class MyVaadinUI extends UI
{

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);

		// Have a Panel where to put the custom layout.
		Panel panel = new Panel("Login");
		panel.setSizeUndefined();
		layout.addComponent(panel);

		// Create custom layout from "layoutname.html" template.
		CustomLayout custom = new CustomLayout("login");
		custom.addStyleName("customlayoutexample");

		// Use it as the layout of the Panel.
		panel.setContent(custom);

		// Create a few components and bind them to the location tags
		// in the custom layout.
		TextField username = new TextField();
		custom.addComponent(username, "username");


		PasswordField password = new PasswordField();
		custom.addComponent(password, "password");


		Button ok = new Button("Login");
		custom.addComponent(ok, "okbutton");
	}

}
