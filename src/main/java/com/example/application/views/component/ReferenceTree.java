package com.example.application.views.component;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class ReferenceTree extends VerticalLayout {
    MenuBar menuBar;
    Text selected;
    Div message;

    Icon icon;
    TextField status;

    public ReferenceTree() {
        menuBar = new MenuBar();//menu container

        selected = new Text("");
        message = new Div(new Text("Clicked item: "), selected);

        ComponentEventListener<ClickEvent<MenuItem>> listener = e -> selected.setText(e.getSource().getText());

        MenuItem root = menuBar.addItem("Alle Kategorien", listener);
        SubMenu subMenu1 = root.getSubMenu();

        icon = getIcon();
        menuBar.addItem(icon, e -> iconClickEventHandler(icon, e));

        MenuItem finanz = subMenu1.addItem("Finanzierung", listener);
        Span span1 = new Span();


        MenuItem dataType = subMenu1.addItem("Datentypen", listener);
        SubMenu dataTypeSubMenu = dataType.getSubMenu();
        MenuItem gewebeproben = dataTypeSubMenu.addItem("Gewebeproben", listener);
        SubMenu gewebeprobenSubMenu = gewebeproben.getSubMenu();
        gewebeprobenSubMenu.addItem("Blueprobe", listener);
        gewebeprobenSubMenu.addItem("Biopsie der Haut", listener);
        dataTypeSubMenu.addItem("Persönliche Daten", listener);

        MenuItem research = subMenu1.addItem("Forschungsbereich", listener);
        SubMenu researchSubMenu = research.getSubMenu();
        researchSubMenu.addItem("Krebsforschung", listener);
        researchSubMenu.addItem("Virusforschung", listener);

        add(menuBar, message, status);
    }

    private Icon getIcon(){
        icon = new Icon(VaadinIcon.CIRCLE);//-1 no, 0 , 1 yes
        status = new TextField();
        status.setValue("0");
        //status.setVisible(false);
        return icon;
    }

    private void iconClickEventHandler(Icon icon, ClickEvent<MenuItem> event) {
        if (status.getValue().equals("0")) {
            status.setValue("1");
            icon.setColor("green");
        } else if (status.getValue().equals("1")) {
            status.setValue("-1");
            icon.setColor("red");
        } else {
            status.setValue("0");
            icon.setColor("yellow");
        }
    }

    /*Icon check = new Icon(VaadinIcon.CHECK);
    Icon info = new Icon(VaadinIcon.INFO);
    Icon close = new Icon(VaadinIcon.CLOSE);
    MenuItem item = menu.addItem(icon, e -> {});*/

}
