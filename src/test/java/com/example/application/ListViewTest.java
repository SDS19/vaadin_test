package com.example.application;

import com.example.application.data.entity.Contact;
import com.example.application.views.component.ContactForm;
import com.example.application.views.list.ListView;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.ListDataProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ListViewTest {

    @Autowired
    private ListView listView;

    @Test
    void formVisible_contactSelected() {
        Grid<Contact> grid = listView.grid;
        Contact firstContact = getFirstItem(grid);

        ContactForm form = listView.form;
        Assertions.assertFalse(form.isVisible());
        grid.asSingleSelect().setValue(firstContact);
        Assertions.assertTrue(form.isVisible());
        Assertions.assertEquals(firstContact.getFirstName(), form.firstName.getValue());
    }

    private Contact getFirstItem(Grid<Contact> grid) {
        return ((ListDataProvider<Contact>) grid.getDataProvider()).getItems().iterator().next();
    }
}
