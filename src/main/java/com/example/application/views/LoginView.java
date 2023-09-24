package com.example.application.views;

import com.example.application.models.UserModel;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("") 
@PageTitle("Los Financeros Hermanos - Login")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private final LoginI18n i18n = LoginI18n.createDefault();
    private final LoginI18n.Form i18nForm = i18n.getForm();
    private final LoginForm loginForm = new LoginForm();

    public LoginView(){
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        i18nForm.setTitle("Bem vindo ao Los Financeros Hermanos");
        i18nForm.setUsername("Username");
        i18nForm.setPassword("Senha");
        i18nForm.setSubmit("Login");
        i18nForm.setForgotPassword("Esqueci minha senha");
         loginForm.addForgotPasswordListener(e -> {
            UI.getCurrent().navigate(ForgetPasswordView.class);
        });
        i18n.setForm(i18nForm);
    
        LoginI18n.ErrorMessage i18nErrorMessage = i18n.getErrorMessage();
        i18nErrorMessage.setTitle("Usuário ou senha inválido!");
        i18nErrorMessage.setMessage("Favor tentar inserir seu usuário novamente");
        i18n.setErrorMessage(i18nErrorMessage);
    
        loginForm.setI18n(i18n);

        loginForm.addLoginListener(e -> {
            boolean isAuthenticated = UserModel.checkCredentials(e.getUsername(), e.getPassword());
            if (isAuthenticated) {
                e.getSource().getUI().ifPresent(ui -> ui.navigate(InicioView.class));
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
