package com.example.application.views;

import com.example.application.controllers.GainController;
import com.example.application.controllers.SpentController;
import com.example.application.models.GainModel;
import com.example.application.models.SpentModel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import java.util.Date;

@Route("crud")
public class CrudView extends VerticalLayout {

    private final TextField ganhoTipoField = new TextField("Tipo de Ganho");
    private final DatePicker ganhoDataPicker = new DatePicker("Data de Ganho");
    private final NumberField ganhoValorField = new NumberField("Valor de Ganho");

    private final TextField gastoTipoField = new TextField("Tipo de Gasto");
    private final DatePicker gastoDataPicker = new DatePicker("Data de Gasto");
    private final NumberField gastoValorField = new NumberField("Valor de Gasto");
    private final TextField gastoFormaPagamentoField = new TextField("Forma de Pagamento");

    private final Grid<GainController> ganhoGrid = new Grid<>(GainController.class);
    private final Grid<SpentController> gastoGrid = new Grid<>(SpentController.class);

    private final Button salvarGanhoButton = new Button("Salvar Ganho");
    private final Button salvarGastoButton = new Button("Salvar Gasto");

    public CrudView() {
        // Configuração dos botões de salvamento
        salvarGanhoButton.addClickListener(e -> salvarGanho());
        salvarGastoButton.addClickListener(e -> salvarGasto());

        // Configuração dos grids de exibição
        ganhoGrid.setColumns("tipo", "data", "valor");
        gastoGrid.setColumns("tipo", "data", "valor", "formaPagamento");

        // Adiciona componentes ao layout
        add(createGanhoForm(), salvarGanhoButton, ganhoGrid, createGastoForm(), salvarGastoButton, gastoGrid);
        listarGanhos();
        listarGastos();
    }

    private FormLayout createGanhoForm() {
        FormLayout form = new FormLayout();
        form.add(ganhoTipoField, ganhoDataPicker, ganhoValorField);
        return form;
    }

    private FormLayout createGastoForm() {
        FormLayout form = new FormLayout();
        form.add(gastoTipoField, gastoDataPicker, gastoValorField, gastoFormaPagamentoField);
        return form;
    }

    private void salvarGanho() {
        String tipo = ganhoTipoField.getValue();
        Date data = java.util.Date.from(ganhoDataPicker.getValue().atStartOfDay().toInstant(java.time.ZoneOffset.UTC));
        double valor = ganhoValorField.getValue();
        GainModel.insert(tipo, data, valor);
        listarGanhos();
        limparCamposGanho();
    }

    private void salvarGasto() {
        String tipo = gastoTipoField.getValue();
        Date data = java.util.Date.from(gastoDataPicker.getValue().atStartOfDay().toInstant(java.time.ZoneOffset.UTC));
        double valor = gastoValorField.getValue();
        String formaPagamento = gastoFormaPagamentoField.getValue();
        SpentModel.insert(tipo, data, valor, formaPagamento);
        listarGastos();
        limparCamposGasto();
    }

    private void listarGanhos() {
        ganhoGrid.setItems(GainModel.getAll());
    }

    private void listarGastos() {
        gastoGrid.setItems(SpentModel.getAll());
    }

    private void limparCamposGanho() {
        ganhoTipoField.clear();
        ganhoDataPicker.clear();
        ganhoValorField.clear();
    }

    private void limparCamposGasto() {
        gastoTipoField.clear();
        gastoDataPicker.clear();
        gastoValorField.clear();
        gastoFormaPagamentoField.clear();
    }
}
