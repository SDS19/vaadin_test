package com.example.application.data.ViCon.views;

import com.example.application.views.demoView.PersonView;
import com.example.application.views.list.ListView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {
    public MainLayout(){
        getHeader();
        getDrawer();
    }

    private void getHeader() {
        H1 logo = new H1("Vaadin CRM");
        logo.addClassNames("text-l", "m-m");
        HorizontalLayout header = new HorizontalLayout(
                new DrawerToggle(), logo
        );
        header.setDefaultVerticalComponentAlignment(
                FlexComponent.Alignment.CENTER
        );
        header.setWidth("100%");
        header.addClassNames("py-0", "px-m");
        addToNavbar(header);
    }

    private void getDrawer() {
        RouterLink listLink = new RouterLink("List", ListView.class);
        RouterLink treeLink = new RouterLink("Tree", TreeView.class);
        RouterLink binderLink = new RouterLink("Binder", PersonView.class);

        listLink.setHighlightCondition(HighlightConditions.sameLocation());
        addToDrawer(new VerticalLayout(listLink,treeLink, binderLink));
    }
}
