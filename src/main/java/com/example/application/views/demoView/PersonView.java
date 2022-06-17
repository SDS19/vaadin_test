package com.example.application.views.demoView;

import com.example.application.data.entity.Person;
import com.example.application.views.MainLayout;
import com.example.application.views.component.PersonForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Data Binder | Vaading CRM")
@Route(value = "data", layout = MainLayout.class)
public class PersonView extends FormLayout {

    Person person;
    PersonForm form1;
    PersonForm form2;

    public PersonView() {
        person = new Person("Dr.","John Doe","john.doe@isst.de");

        form1 = new PersonForm(false);
        form2 = new PersonForm(true);
        form2.getBinder().readBean(person);

        VerticalLayout layout1 = new VerticalLayout();
        HorizontalLayout btns = new HorizontalLayout();

        Button saveBtn = new Button("Save");
        saveBtn.addClickListener(click -> {
            try {
                form1.getBinder().writeBean(person);
                form2.getBinder().readBean(person);
            } catch (ValidationException e) {
                e.printStackTrace();
            }
        });
        Button restBtn = new Button("Reset");
        restBtn.addClickListener(click -> form1.getBinder().readBean(person));

        btns.add(saveBtn, restBtn);
        layout1.add(form1,btns);

        VerticalLayout layout2 = new VerticalLayout();
        layout2.add(form2);

        HorizontalLayout layout = new HorizontalLayout();
        layout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.START);
        layout.add(layout1, layout2);

        add(layout);
    }

}
