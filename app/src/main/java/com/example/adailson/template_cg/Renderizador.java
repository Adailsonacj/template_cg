package com.example.adailson.template_cg;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

//essa classe implementa os métodos necessários para
//utilizar a biblioteca OPENGL no desenho gráfico que
// sera apresentado na tela pela superficie de desenho
class Renderizador implements GLSurfaceView.Renderer, View.OnTouchListener {
    int fps;
    float inicial = 0;
    float atual = 0;
    FloatBuffer bufferPont;
    FloatBuffer bufferQua;
    float posX = 0;
    float posY = 0;
    float direcaoY = 1;
    float direcaox = 1;
    int alturaY = 0;
    int larguraX = 0;
    float angulo = 0;
    boolean subindo;
    int milisegundo;
    public static int segundo = 0;
    public static int minuto;
    public static int hora;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //Este método é chamado uma vez quando a superfície de desenho é criada.
        //inicial = System.currentTimeMillis();

        //atual = System.currentTimeMillis();

        //Configura a cor d limpeza no formato RGBA
        gl.glClearColor(0, 0, 0, 0);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int largura, int altura) {
        //É chamado quando a superfície de desenho for alterada.

        larguraX = largura;
        alturaY = altura;
        //Configurando a área de cordenadas do plano cartesiano
        //MAtriz de projeção
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();

        //Define o espaço de trabalho.
        //volume (CUBO 3D) de renderização - Tudo que for configurado dentro desse volume aparece na tela.
        //Definindo X - Y - Z , LARGURA - ALTURA - PROFUNDIDADE
        gl.glOrthof(0.0f, largura, 0.0f, altura, -1.0f, 1.0f);

        //OPENGL aponta para nova matriz (De transformações geométricas)
        //Translação, Rotação e Escala
        //Matriz de câmera
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glViewport(0, 0, largura, altura);

        float[] vetorPont = {
                -10, 350,
                -10, 0,
                10, 350,
                10, 0,
        };
        float[] vetorQuadrado = {
                -400, 400,
                -400, -400,
                400, 400,
                400, -400,
        };

        bufferPont = criaNIOBuffer(vetorPont);
        bufferQua = criaNIOBuffer(vetorQuadrado);
    }

    FloatBuffer criaNIOBuffer(float[] coordenadas) {
        //Aloca a qtd de bytes necessárias para armazenar os dados dos vertices
        ByteBuffer vetBytes = ByteBuffer.allocateDirect(coordenadas.length * 4);

        //Usa o sistema de endereçamento de memória
        //nativo no processador em questão
        vetBytes.order(ByteOrder.nativeOrder());

        //cria o FloatBuffer a partir do byteBuffer
        FloatBuffer buffer = vetBytes.asFloatBuffer();

        //Limpa um eventual lixo de memória
        buffer.clear();

        //Encapsula o array java no objeto Float Buffer
        buffer.put(coordenadas);

        //Retira as sobras de memória
        buffer.flip();

        //Retorna o objeto de coordenadas
        return buffer;
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        //Este método é chamado n vezes por segundo para desenhar na tela.
        //(Determina o FPS)
        //Solicita openGL permissao para usar vetores de vertices

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glRotatef(0, 0, 0, 1);
        gl.glTranslatef(larguraX/2, alturaY/2, 0);
        gl.glColor4f(0, 0, 1, 0);
        gl.glVertexPointer(2, GL10.GL_FLOAT, 0, bufferQua);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

        gl.glPushMatrix();
        //Ponteiro da hora
        gl.glRotatef(hora, 0, 0, 1);
        gl.glColor4f(1, 1, 0, 0);
        gl.glVertexPointer(2, GL10.GL_FLOAT, 0, bufferPont);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

        //Ponteiro do minuto
        gl.glScalef(0.80f, 0.80f, 0.80f);
        gl.glColor4f(0, 1, 0, 0);
        gl.glRotatef(minuto - hora, 0, 0, 1);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

        //Ponteiro do segundo
        gl.glScalef(0.60f, 0.60f, 0.60f);
        gl.glColor4f(1, 0, 0, 0);
        gl.glRotatef(segundo - minuto, 0, 0, 1);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
        gl.glPopMatrix();

        gl.glLoadIdentity();

        angulo ++;

        atual = angulo;

        if (atual-inicial>60){
            segundo += -6;
            angulo = 0;
            inicial = angulo;

        }
        if(segundo < -360){
            segundo = -6;
            minuto += -6;
        }
        if(minuto < -360){
            minuto = -6;
            hora += -6;
        }

        Log.i("AAA", "Segundo = " + segundo);



        /*
        //Transformação geométrica
        gl.glRotatef(45 , 1, 0, 0);

        atual = System.currentTimeMillis();

        fps++;

        if (atual - inicial > 1000) {
            //Sorteia três valores entre zero e um para cada uma dessas variáveis
            float vermelho = (float)Math.random();
            float verde = (float)Math.random();
            float azul = (float)Math.random();

            gl.glClearColor(vermelho, verde, azul, 1.0f);


            Log.i("FPS", "FPS = " + fps);
            fps = 0;
            inicial = System.currentTimeMillis();
        }
        */

        //Desenhar interpretando os vértices como triangulos (3 vertices)
        //iniciando da pos 0 até a 3 posição

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
            posX = (int) motionEvent.getX();
            posY = alturaY - (int) motionEvent.getY();
        } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {

        }
        return true;
    }
}
