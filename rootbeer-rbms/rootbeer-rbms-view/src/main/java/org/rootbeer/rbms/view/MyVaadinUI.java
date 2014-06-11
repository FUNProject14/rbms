package org.rootbeer.rbms.view;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
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
		// Set the page title (window or tab caption)
		Page.getCurrent().setTitle("しょうがないにゃぁ・・");
		
		final VerticalLayout layout1 = new VerticalLayout();
		layout1.setMargin(true);
		setContent(layout1);
		
		final HorizontalLayout layout2 = new HorizontalLayout();
		layout2.setMargin(true);
		setContent(layout2);
		

		// Have a Panel where to put the custom layout.
		Panel panel1 = new Panel();
		Panel panel2 = new Panel("Login");
		Panel panel3 = new Panel("test");
		panel1.setSizeFull();
		panel2.setSizeUndefined();
		panel3.setSizeUndefined();
		layout1.addComponent(panel1);
		layout2.addComponent(panel2);
		layout2.addComponent(panel3);

		// Create custom layout from "layoutname.html" template.
		CustomLayout custom1 = new CustomLayout("layout03");
		CustomLayout custom2 = new CustomLayout("layout02");
		CustomLayout custom3 = new CustomLayout("layout04");
		custom1.addStyleName("customlayoutexample");
		custom2.addStyleName("cutsomlayoutexample2");
		custom3.addStyleName("cutsomlayoutexample3");
		

		// Use it as the layout of the Panel.
		panel1.setContent(custom1);
		panel2.setContent(custom2);
		panel3.setContent(custom3);

		// Create a few components and bind them to the location tags
		// in the custom layout.
		Label label1 = new Label("test1");
		custom1.addComponent(label1,"label1");
		
		TextField username = new TextField();
		custom2.addComponent(username, "username");

		PasswordField password = new PasswordField();
		custom2.addComponent(password, "password");


		Button ok = new Button("Login");
		custom2.addComponent(ok, "okbutton");
		
		Label label2 = new Label("test2");
		custom3.addComponent(label2, "label2");
	}

}
