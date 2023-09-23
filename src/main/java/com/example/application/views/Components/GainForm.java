package com.example.application.views.Components;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;

public class GainForm extends FormLayout {
  TextField ganhoTipoField = new TextField("Tipo de Ganho");
  DatePicker ganhoDataPicker = new DatePicker("Data de Ganho");
  NumberField ganhoValorField = new NumberField("Valor de Ganho");

  Button save = new Button("Salvar");
  Button delete = new Button("Deletar");
  Button close = new Button("Cancelar");

  public GainForm() {
    addClassName("contact-form");

    add(ganhoTipoField,
        ganhoDataPicker,
        ganhoValorField,
        createButtonsLayout());
  }

  private HorizontalLayout createButtonsLayout() {
    save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
    close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

    save.addClickShortcut(Key.ENTER);
    close.addClickShortcut(Key.ESCAPE);

    return new HorizontalLayout(save, delete, close);
  }
}