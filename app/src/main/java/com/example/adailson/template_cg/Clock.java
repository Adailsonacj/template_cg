package com.example.adailson.template_cg;

public class Clock {
    private int segundo;
    private int minuto;
    private int hora;

    public Clock(int segundo, int minuto, int hora) {
        this.segundo = segundo;
        this.minuto = minuto;
        this.hora = hora;
    }

    public int getSegundo() {
        return segundo*-6;
    }

    public void setSegundo(int segundo) {
        this.segundo = segundo;
    }

    public int getMinuto() {
        return minuto*-6;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public int getHora() {
        return hora*-30;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }
}
