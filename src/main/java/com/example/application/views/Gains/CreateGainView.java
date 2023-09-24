package com.example.application.views.Gains;

import com.example.application.models.GainModel;
import com.example.application.views.Layout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import java.util.Date;

@Route(value = "create/gain", layout = Layout.class) 
public class CreateGainView extends VerticalLayout {

    

    private final TextField ganhoTipoField = new TextField("Tipo de Ganho");
    private final DatePicker ganhoDataPicker = new DatePicker("Data de Ganho");
    private final NumberField ganhoValorField = new NumberField("Valor de Ganho");

    private final Button salvarGanhoButton = new Button("Salvar Ganho");

    
    public CreateGainView() {
        salvarGanhoButton.addClickListener(e -> {
            salvarGanho();
            UI.getCurrent().navigate(GainView.class);
        });
        
        add(createGanhoForm(), salvarGanhoButton);
    }

    private FormLayout createGanhoForm() {
        FormLayout form = new FormLayout();
        form.add(ganhoTipoField, ganhoDataPicker, ganhoValorField);
        return form;
    }

    private void salvarGanho() {
        String tipo = ganhoTipoField.getValue();
        Date data = java.util.Date.from(ganhoDataPicker.getValue().atStartOfDay().toInstant(java.time.ZoneOffset.UTC));
        double valor = ganhoValorField.getValue();
        GainModel.insert(tipo, data, valor);
        limparCamposGanho();
    }

    private void limparCamposGanho() {
        ganhoTipoField.clear();
        ganhoDataPicker.clear();
        ganhoValorField.clear();
    }

}
