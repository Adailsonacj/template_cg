package com.example.adailson.template_cg;

public class Clock {
    private float segundo;
    private float minuto;
    private float hora;

    public Clock(float segundo, float minuto, float hora) {
        this.segundo = segundo;
        this.minuto = minuto;
        this.hora = hora;
    }

    public float getSegundo() {
        return segundo;
    }

    public void setSegundo(float segundo) {
        this.segundo = segundo;
    }

    public float getMinuto() {
        return minuto;
    }

    public void setMinuto(float minuto) {
        this.minuto = minuto;
    }

    public float getHora() {
        return hora;
    }

    public void setHora(float hora) {
        this.hora = hora;
    }
}
