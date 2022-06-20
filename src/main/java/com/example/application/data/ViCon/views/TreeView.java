package com.example.application.data.ViCon.views;

import com.example.application.data.ViCon.entity.PreferenceItem;
import com.example.application.data.ViCon.service.PatientService;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@PageTitle("Reference Tree")
@Route(value = "tree", layout = MainLayout.class)
public class TreeView extends VerticalLayout {

    private PreferenceItem preferenceItem;
    private Accordion tree;
    private Grid<PreferenceItem> grid;

    private PatientService service;
    private List<PreferenceItem> itemList;
    private Map<String,PreferenceItem> itemMap;


    public TreeView(PatientService service) {
        this.service = service;

        itemList = service.getPreferenceByPatientId(service.getPatient("patient_1").getId());
        itemMap = new HashMap<>();
        for (PreferenceItem item : itemList) itemMap.put(item.getName(), item);

        tree = getPreferenceTree(itemMap);

        add(getButtonLayout(), tree);
    }

    private Accordion getPreferenceTree(Map<String,PreferenceItem> itemMap) {
        tree = new Accordion();

        /****************************** Finanzierung ******************************/

        PreferenceItem financeItem = itemMap.get("Finanzierung");

        TextField financeStatus = new TextField();
        financeStatus.setValue(financeItem.getStatus());

        Button button = new Button("Click");
        button.addClickListener(e -> clickEventHandler(e,financeStatus,financeItem));
        itemMap.put("Finanzierung",financeItem);

        /*Button testButton = new Button("Test");
        testButton.addClickListener(e ->  System.out.println(itemMap.get("Finanzierung").getStatus()));*/

        AccordionPanel finance = tree.add("Finanzierung",new Span(financeStatus,button));//addAccordionPanel(tree,"Finanzierung");

        /****************************** Datentypen ******************************/

        Accordion gewerbeproben = new Accordion();

        AccordionPanel blutprobe = addAccordionPanel(gewerbeproben,"Blutprobe");
        AccordionPanel hautBiopsie = addAccordionPanel(gewerbeproben,"Biopsie der Haut");

        VerticalLayout gewerbeprobenLayout = new VerticalLayout(blutprobe, hautBiopsie);
        gewerbeprobenLayout.setSpacing(false);
        gewerbeprobenLayout.setPadding(false);

        gewerbeproben.add("Gewerbeproben", gewerbeprobenLayout);

        Accordion personalDate = new Accordion();

        personalDate.add("Persönliche Daten",getFieldAndButton("Persönliche Daten"));

        VerticalLayout dataTypeLayout = new VerticalLayout(gewerbeproben, personalDate);
        dataTypeLayout.setSpacing(false);
        dataTypeLayout.setPadding(false);

        AccordionPanel dataType = tree.add("DatenTypen", dataTypeLayout);

        /****************************** Forschungsbereich ******************************/

        Accordion krebsforschung = new Accordion();
        krebsforschung.add("Krebsforschung",getFieldAndButton("Krebsforschung"));

        Accordion virusforschung = new Accordion();
        virusforschung.add("Virusforschung",getFieldAndButton("Virusforschung"));

        VerticalLayout researchLayout = new VerticalLayout(krebsforschung, virusforschung);
        researchLayout.setSpacing(false);
        researchLayout.setPadding(false);

        AccordionPanel research = tree.add("Forschungsbereich", researchLayout);

        /****************************** Alle Kategorien ******************************/

        finance.addThemeVariants(DetailsVariant.FILLED);
        dataType.addThemeVariants(DetailsVariant.FILLED);
        research.addThemeVariants(DetailsVariant.FILLED);

        VerticalLayout treeLayout = new VerticalLayout(finance, dataType, research);
        treeLayout.setSpacing(false);
        treeLayout.setPadding(false);

        tree.add("Alle Kategorien", treeLayout);
        return  tree;
    }

    private AccordionPanel addAccordionPanel(Accordion parent, String summary){
        return parent.add(summary, getFieldAndButton(summary));
    }

    private Span getFieldAndButton(String componentId){
        TextField statusField = new TextField();
        statusField.setValue("unselected");

        Button button = new Button("Click");
        button.setId(componentId);
        button.addClickListener(e -> {
            if ("unselected".equals(statusField.getValue())) {
                statusField.setValue("confirmed");
                String id = e.getSource().getId().toString();
            }
            else if ("confirmed".equals(statusField.getValue())) {
                statusField.setValue("denied");
            } else {
                statusField.setValue("unselected");
            }
        });

        Span span = new Span(statusField,button);
        return span;
    }

    private void clickEventHandler(ClickEvent<Button> e,TextField statusField, PreferenceItem preferenceItem){
        if ("unselected".equals(statusField.getValue())) {
            statusField.setValue("confirmed");
            preferenceItem.setStatus("confirmed");
        } else if ("confirmed".equals(statusField.getValue())) {
            statusField.setValue("denied");
            preferenceItem.setStatus("denied");
        } else {
            statusField.setValue("unselected");
            preferenceItem.setStatus("unselected");
        }
    }

    private HorizontalLayout getButtonLayout(){
        Button save = new Button("Save");
        Button reset = new Button("Reset");

        save.addClickListener(e -> updatePreference());
        reset.addClickListener(e -> {});

        return new HorizontalLayout(save, reset);
    }

    //save button click event
    private void updatePreference(){
        List<PreferenceItem> list = new ArrayList<>();
        for (PreferenceItem item : itemMap.values()) list.add(item);
        service.updatePatientPreference(list);
    }



}
