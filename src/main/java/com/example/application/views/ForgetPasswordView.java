package com.example.application.views;

import com.example.application.models.UserModel;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("redefinir-senha") 
@PageTitle("Los Financeros Hermanos - Redefinir senha")
public class ForgetPasswordView extends VerticalLayout implements BeforeEnterObserver {

    private final LoginI18n i18n = LoginI18n.createDefault();
    private final LoginI18n.Form i18nForm = i18n.getForm();
    private final LoginForm loginForm = new LoginForm();

    public ForgetPasswordView(){
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        i18nForm.setTitle("Redefinir senha!");
        i18nForm.setUsername("Usuário que sera alterado a senha");
        i18nForm.setPassword("Senha nova");
        i18nForm.setSubmit("Redefinir");
        i18nForm.setForgotPassword(null);
        i18n.setForm(i18nForm);
    
        LoginI18n.ErrorMessage i18nErrorMessage = i18n.getErrorMessage();
        i18nErrorMessage.setTitle("Senhas não são iguais");
        i18nErrorMessage.setMessage("Favor tentar inserir seu usuário novamente");
        i18n.setErrorMessage(i18nErrorMessage);
    
        loginForm.setI18n(i18n);

        loginForm.addLoginListener(e -> {
            boolean isChangePassword = UserModel.changePassword(e.getUsername(), e.getPassword());
            if (isChangePassword) {
                e.getSource().getUI().ifPresent(ui -> ui.navigate(LoginView.class));
            } else {
                loginForm.setError(true);
            }
        });

        add(loginForm);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            loginForm.setError(true);
        }
    }
}
