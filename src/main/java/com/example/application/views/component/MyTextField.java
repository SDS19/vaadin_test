package com.example.application.views.component;

import com.example.application.views.event.ChangeEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Synchronize;
import com.vaadin.flow.component.Tag;

@Tag("input")
public class MyTextField extends Component {

    public MyTextField(String value) {
        getElement().setProperty("value", value);
    }

    @Synchronize("change")
    public String getValue() {
        return getElement().getProperty("value");
    }

    public void setValue(String value) {
        getElement().setProperty("value", value);
        fireEvent(new ChangeEvent(this, false));
    }
}
