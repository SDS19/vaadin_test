package com.example.application.views.demoView;

import com.example.application.data.entity.Contact;
import com.example.application.views.MainLayout;
import com.example.application.views.component.ReferenceTree;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Reference Tree")
@Route(value = "tree", layout = MainLayout.class)
public class TreeView extends VerticalLayout {

    private Accordion root;

    private AccordionPanel finance;
    private AccordionPanel dataType;
    private AccordionPanel research;

    private Accordion gewerbeproben;

    private AccordionPanel blutprobe;
    private AccordionPanel hautBiopsie;

    public TreeView() {
        Accordion root = initAccordion();

        ReferenceTree tree = new ReferenceTree();
        //finance.add("Finanzierung", new Button("button1"));
        //dataType.add("DatenTypen",new Button("button2"));
        //research.add("Forschungsbereich",new Button("button3"));

        add(root,tree);
    }

    private Accordion initAccordion() {
        root = new Accordion();

        finance = root.add("Finanzierung", new Button("Hello"));
        dataType = root.add("DatenTypen",class_1());
        research = root.add("Forschungsbereich",new Button("button3"));

        finance.addThemeVariants(DetailsVariant.FILLED);
        dataType.addThemeVariants(DetailsVariant.FILLED);
        research.addThemeVariants(DetailsVariant.FILLED);
        //research = new Accordion();

        VerticalLayout rootLayout = new VerticalLayout(finance, dataType, research);
        rootLayout.setSpacing(false);
        rootLayout.setPadding(false);

        root.add("Alle Kategorien", rootLayout);
        return  root;
    }

    private Accordion class_1() {
        gewerbeproben = new Accordion();
        blutprobe = gewerbeproben.add("Blutprobe", new Text("Hello"));
        hautBiopsie = gewerbeproben.add("Biopsie der Haut", new Text("World!"));

        ComboBox<Contact> contact = new ComboBox<>();
        contact.setItemLabelGenerator(Contact::getFirstName);

        VerticalLayout classLayout_1 = new VerticalLayout(blutprobe, hautBiopsie, contact);
        classLayout_1.setSpacing(false);
        classLayout_1.setPadding(false);

        gewerbeproben.add("Gewerbeproben", classLayout_1);

        return gewerbeproben;
    }

}
