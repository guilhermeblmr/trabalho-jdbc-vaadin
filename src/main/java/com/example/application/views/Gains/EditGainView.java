package com.example.application.views.Gains;

import com.example.application.controllers.GainController;
import com.example.application.models.GainModel;
import com.example.application.views.Layout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "edit/gain", layout = Layout.class)
public class EditGainView extends VerticalLayout implements HasUrlParameter<String> {

    private final TextField ganhoTipoField = new TextField("Tipo de Ganho");
    private final DatePicker ganhoDataPicker = new DatePicker("Data de Ganho");
    private final NumberField ganhoValorField = new NumberField("Valor de Ganho");

    private final Button salvarGanhoButton = new Button("Salvar Ganho");
    private final Button cancelarButton = new Button("Cancelar");

    private final GainModel gainModel;

    @Autowired
    public EditGainView(GainModel gainModel) {
        this.gainModel = gainModel;

        // Configuração dos botões de ação
        salvarGanhoButton.addClickListener(e -> salvarGanho());
        cancelarButton.addClickListener(e -> cancelarEdicao());

        // Adiciona componentes ao layout
        FormLayout form = createGanhoForm();
        form.add(salvarGanhoButton, cancelarButton);
        add(form);
    }

    private FormLayout createGanhoForm() {
        FormLayout form = new FormLayout();
        form.add(ganhoTipoField, ganhoDataPicker, ganhoValorField);
        return form;
    }

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
        if (parameter != null) {
            // Carregar dados do ganho com base no parâmetro (ID ou outro identificador)
            GainController gain = gainModel.getGainById(parameter);
            if (gain != null) {
                // Preencher campos do formulário com dados do ganho
                ganhoTipoField.setValue(gain.getTipo());
                ganhoDataPicker.setValue(gain.getData());
                ganhoValorField.setValue(gain.getValor());
            }
        }
    }

    private void salvarGanho() {
        String tipo = ganhoTipoField.getValue();
        java.util.Date data = java.util.Date.from(ganhoDataPicker.getValue().atStartOfDay().toInstant(java.time.ZoneOffset.UTC));
        double valor = ganhoValorField.getValue();
        
        // Atualizar os dados do ganho no modelo (utilize um método adequado)
        // gainModel.updateGainById(parameter, tipo, data, valor);
        
        // Redirecionar para a visualização de ganhos após a edição
        UI.getCurrent().navigate(GainView.class);
    }

    private void cancelarEdicao() {
        // Redirecionar para a visualização de ganhos sem fazer alterações
        UI.getCurrent().navigate(GainView.class);
    }
}
