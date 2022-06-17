package com.example.application.views.demoView;

import com.example.application.data.entity.BusinessPerson;
import com.example.application.views.MainLayout;
import com.example.application.views.component.PersonEditor;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Data Binding | Vaading CRM")
@Route(value = "binder", layout = MainLayout.class)
public class DataBindingView extends VerticalLayout {

    BusinessPerson person;
    PersonEditor editor1;
    PersonEditor editor2;

    public DataBindingView() {
        person = new BusinessPerson();
        editor1 = new PersonEditor(false);
        editor2 = new PersonEditor(true);

        VerticalLayout layout1 = new VerticalLayout();
        HorizontalLayout btns = new HorizontalLayout();

        Button saveBtn = new Button("Save");
        saveBtn.addClickListener(click -> {
            try {
                editor1.getBinder().writeBean(person);
                editor2.getBinder().readBean(person);
            } catch (ValidationException e) {
                e.printStackTrace();
            }
        });
        Button restBtn = new Button("Reset");
        restBtn.addClickListener(click -> editor1.getBinder().readBean(person));

        btns.add(saveBtn, restBtn);
        layout1.add(editor1, btns);

        VerticalLayout layout2 = new VerticalLayout();
        layout2.add(editor2);

        HorizontalLayout layout = new HorizontalLayout();
        layout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.START);
        layout.add(layout1, layout2);

        add(layout);
    }

}
