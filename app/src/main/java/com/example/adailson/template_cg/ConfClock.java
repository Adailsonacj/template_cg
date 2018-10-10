package com.example.adailson.template_cg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ConfClock extends AppCompatActivity {

    EditText segundos;
    EditText minutos;
    EditText horas;
    Button configurar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conf_clock);

        this.segundos = (EditText) findViewById(R.id.segundos);
        this.minutos = (EditText) findViewById(R.id.minutos);
        this.horas = (EditText) findViewById(R.id.hora);
        this.configurar = findViewById(R.id.button);
    }

    public void configurar(View view) {
        int segundos = Integer.parseInt(String.valueOf(this.segundos.getText()));
        int minutos = Integer.parseInt(String.valueOf(this.minutos.getText()));
        int horas = Integer.parseInt(String.valueOf(this.horas.getText()));
        Clock confHora = new Clock( segundos, minutos,horas);
        Renderizador.segundo = confHora.getSegundo();
        Renderizador.minuto=confHora.getMinuto();
        Renderizador.hora=confHora.getHora();

        Intent vrintention = new Intent(this, MainActivity.class);
        startActivity(vrintention);
    }
}
