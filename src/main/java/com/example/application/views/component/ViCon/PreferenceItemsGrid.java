package com.example.application.views.component.ViCon;

import com.example.application.data.ViCon.entity.PreferenceItem;
import com.example.application.data.ViCon.service.PreferenceTreeManager;
import com.vaadin.flow.component.grid.Grid;

public class PreferenceItemsGrid {
    public Grid<PreferenceItem> grid = new Grid<>(PreferenceItem.class);

    public PreferenceItemsGrid(PreferenceTreeManager service){

    }

    private void configureGrid() {
        grid.addColumn(preferenceItem -> preferenceItem.getName()).setHeader("Name");
        grid.addColumn(preferenceItem -> preferenceItem.getStatus()).setHeader("Status");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void savePreference(){

    }

    private void updateGrid(){

    }
}