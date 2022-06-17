package com.example.application.views.demoView;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Demo")
@Route(value = "todo")
public class Todo extends VerticalLayout{
    private VerticalLayout todosList;
    private TextField taskField;
    private Button addButton;

    public Todo(){
        todosList = new VerticalLayout();
        taskField = new TextField();
        addButton = new Button("Add");
        addButton.addClickListener(e -> {
            Checkbox checkbox = new Checkbox(taskField.getValue());
            todosList.add(checkbox);
        });
        addButton.addClickShortcut(Key.ENTER);

        add(new H1("Vaadin Todo"), todosList, new HorizontalLayout(taskField, addButton));
    }
}
