package com.example.application.controllers;

import java.util.Date;

public class GainController {
    private String tipo;
    private Date data;
    private double valor;

    public GainController(String tipo, Date date, double valor) {
        this.tipo = tipo;
        this.data = date;
        this.valor = valor;
    }

    public String getId() {
        return tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
