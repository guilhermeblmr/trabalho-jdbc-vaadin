package com.example.application.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class Layout extends AppLayout { 

    public Layout() {
        createHeader();
    }

    private void createHeader() {
        H1 logo = new H1("Los Financeros Hermanos");
        logo.addClassNames(
            LumoUtility.FontSize.LARGE, 
            LumoUtility.Margin.MEDIUM);
        
        HorizontalLayout menu = new HorizontalLayout( 
                    new RouterLink("List", ListView.class),
                    new RouterLink("login", LoginView.class)
            );

            
            var header = new HorizontalLayout( logo ); 
            
            header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER); 
            header.setWidthFull();
            header.addClassNames(
                LumoUtility.Padding.Vertical.NONE,
                LumoUtility.Padding.Horizontal.MEDIUM);

            menu.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER); 
            menu.setWidthFull();
            menu.addClassNames(
                LumoUtility.Padding.Vertical.NONE,
                LumoUtility.Padding.Horizontal.MEDIUM);
                
            addToNavbar(header); 
            addToNavbar(menu);

    }
}