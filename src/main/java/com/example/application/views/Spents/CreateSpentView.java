package com.example.application.views.Spents;

import com.example.application.models.SpentModel;
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

@Route(value = "create/spent", layout = Layout.class) 
public class CreateSpentView extends VerticalLayout {

    

    private final TextField gastoTipoField = new TextField("Tipo de Gasto");
    private final DatePicker gastoDataPicker = new DatePicker("Data de Gasto");
    private final NumberField gastoValorField = new NumberField("Valor de Gasto");
    private final TextField gastoFormaPagamentoField = new TextField("Forma de Pagamento");

    private final Button salvarGastoButton = new Button("Salvar Gasto");

    
    public CreateSpentView() {
        salvarGastoButton.addClickListener(e -> {
            salvarGasto();
            UI.getCurrent().navigate(SpentView.class);
        });
        
        add(createGastoForm(), salvarGastoButton);
    }

    private FormLayout createGastoForm() {
        FormLayout form = new FormLayout();
        form.add(gastoTipoField, gastoDataPicker, gastoValorField, gastoFormaPagamentoField);
        return form;
    }

    private void salvarGasto() {
        String tipo = gastoTipoField.getValue();
        Date data = java.util.Date.from(gastoDataPicker.getValue().atStartOfDay().toInstant(java.time.ZoneOffset.UTC));
        double valor = gastoValorField.getValue();
        String formaPagamento = gastoFormaPagamentoField.getValue();
        SpentModel.insert(tipo, data, valor, formaPagamento);
        limparCamposGasto();
    }
    private void limparCamposGasto() {
        gastoTipoField.clear();
        gastoDataPicker.clear();
        gastoValorField.clear();
    }

}
