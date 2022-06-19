package com.example.application.views.list;

import com.example.application.data.entity.Contact;
import com.example.application.data.service.CrmService;
import com.example.application.data.ViCon.views.MainLayout;
import com.example.application.views.component.ContactForm;
import com.example.application.views.component.NumericField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@PageTitle("Contacts | Vaading CRM")
@Route(value = "", layout = MainLayout.class)
public class ListView extends VerticalLayout {//place all child components vertically

    /****************************** components ******************************/

    public TextField filterText = new TextField();

    public Grid<Contact> grid = new Grid<>(Contact.class);//Grid component is typed with Contact
    public ContactForm form;

    public CrmService service;

    /****************************** constructor ******************************/

    public ListView(CrmService service) {//autowire CrmService through the constructor
        this.service = service;

        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureForm();

        add(getToolbar(), getContent());
        updateList();//call this method once you have constructed the view
        closeEditor();
    }

    /****************************** toolbar ******************************/

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);//avoid unnecessary database calls
        filterText.addValueChangeListener(e -> updateList());

        Button addContactBtn = new Button("Add Contact");
        addContactBtn.addClickListener(click -> addContact());

        NumericField field = new NumericField();

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactBtn, field);
        toolbar.addClassNames("toolbar");
        return toolbar;
    }

    /****************************** content: grid + form ******************************/

    private HorizontalLayout getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);//define the Grid get 2 times the space of the form
        content.setFlexGrow(1, form);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureGrid() {
        grid.addClassName("contact-grid");
        grid.setSizeFull();

        grid.setColumns("firstName", "lastName", "email");//define which properties of Contact the grid should show
        grid.addColumn(contact -> contact.getStatus().getName()).setHeader("Status");//define custom columns for nested object
        grid.addColumn(contact -> contact.getCompany().getName()).setHeader("Company");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(e -> editContact(e.getValue()));
    }

    private void configureForm() {
        form = new ContactForm(service.findAllCompanies(), service.findAllStatuses());
        form.setWidth("25em");

        form.addListener(ContactForm.SaveEvent.class, this::saveContact);
        form.addListener(ContactForm.DeleteEvent.class, this::deleteContact);
        form.addListener(ContactForm.CloseEvent.class, e -> closeEditor());
    }

    private void saveContact(ContactForm.SaveEvent event) {
        service.saveContact(event.getContact());
        updateList();
        closeEditor();
    }

    private void deleteContact(ContactForm.DeleteEvent event) {
        service.deleteContact(event.getContact());
        updateList();
        closeEditor();
    }

    private void addContact() {
        grid.asSingleSelect().clear();
        editContact(new Contact());
    }

    private void updateList() {
        grid.setItems(service.findAllContacts(filterText.getValue()));
    }

    /****************************** open / close form ******************************/

    public void editContact(Contact contact) {
        if (contact == null) closeEditor();
        else {
            form.setContact(contact);
            form.setVisible(true);
        }
    }

    private void closeEditor() {
        form.setContact(null);
        form.setVisible(false);
    }
}
