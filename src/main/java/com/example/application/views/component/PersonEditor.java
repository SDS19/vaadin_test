package com.example.application.views.component;

import com.example.application.data.entity.BusinessPerson;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class PersonEditor extends FormLayout {

    private Binder<BusinessPerson> binder;

    public PersonEditor(boolean readOnly){
        binder = new Binder<>(BusinessPerson.class);

        TextField name = new TextField();
        addFormItem(name, "Name");
        binder.bind(name, BusinessPerson::getName, BusinessPerson::setName);
        name.setReadOnly(readOnly);

        TextField title = new TextField();
        addFormItem(title, "Title");
        binder.forField(title).bind(BusinessPerson::getTitle, BusinessPerson::setTitle);
        title.setReadOnly(readOnly);
    }

    public Binder<BusinessPerson> getBinder() {
        return binder;
    }
}
