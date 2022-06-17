package com.example.application.views.component;

import com.example.application.data.entity.Person;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.EmailValidator;

public class PersonForm extends FormLayout {

    private Binder<Person> binder;

    private TextField title;
    private TextField name;
    private TextField email;

    public PersonForm(boolean readOnly){
        binder = new Binder<>(Person.class);

        title = new TextField();
        addFormItem(title, "Title");
        //binder.forField(title).asRequired("Must have a title!").bind(Person::getTitle, Person::setTitle);
        binder.bind(title, "title");
        title.setReadOnly(readOnly);

        name = new TextField();
        addFormItem(name, "Name");
        binder.forField(name)
                .withValidator(n -> n.length() >= 3, "Name must contain at least 3 characters!")
                .bind(Person::getName, Person::setName);
        name.setReadOnly(readOnly);

        email = new TextField();
        addFormItem(email, "E-Mail");
        binder.forField(email).withValidator(new EmailValidator("Invalid E-mail address!"))
                .bind(Person::getEmail, Person::setEmail);
        email.setReadOnly(readOnly);
    }

    public Binder<Person> getBinder() {
        return binder;
    }
}
