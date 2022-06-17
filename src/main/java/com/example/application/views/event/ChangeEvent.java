package com.example.application.views.event;

import com.example.application.views.component.MyTextField;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;

@DomEvent("change")
public class ChangeEvent extends ComponentEvent<MyTextField> {
    public ChangeEvent(MyTextField source, boolean fromClient) {
        super(source, fromClient);
    }
}
