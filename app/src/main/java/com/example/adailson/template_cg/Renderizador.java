package com.example.adailson.template_cg;

import android.opengl.GLSurfaceView;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

//essa classe implementa os métodos necessários para
//utilizar a biblioteca OPENGL no desenho gráfico que
// sera apresentado na tela pela superficie de desenho
class Renderizador implements GLSurfaceView.Renderer {
    int fps;
    long inicial = 0;
    long atual = 0;
    FloatBuffer buffer;
    FloatBuffer buffer2;
    float posX = 0;
    float posY = 0;
    float direcaoY = 1;
    float direcaox = 1;
    int alturaY = 0;
    int larguraX = 0;
    float angulo = 0;


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

        float[] vetorJava = {
                0, 0,
                larguraX, 0,
                larguraX / 2, alturaY / 2,
                larguraX / 2, alturaY / 2,
                larguraX, alturaY,
                0,alturaY,
                0, alturaY/2,
                0,0,
                larguraX/2, alturaY/2,
                larguraX/2, 0,


        };

        buffer = criaNIOBuffer(vetorJava);

        //Registra o NIOBuffer na OpenGL
        gl.glVertexPointer(2, GL10.GL_FLOAT, 0, buffer);

        //Solicita openGL permissao para usar vetores de vertices
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        Triangulo tri = new Triangulo(gl);
        Triangulo tri1 = new Triangulo(gl);
        tri.setCor(0,1,0,0);;
        tri.desenha(0, 3);

        tri1.setCor(1,0,0,0);
        tri1.desenha(3, 3);

        Quadrado qua = new Quadrado(gl);

        qua.setCor(0,0,1,0);
        qua.desenha(6,4);

        //gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        //gl.glColor4f(0, 0, 1, 0);
        //gl.glDrawArrays(GL10.GL_TRIANGLES, 3, 3);

/*
        posX += direcaox * 5;

        if (posX + 50 / 2 >= larguraX || posX <= 0) {
            direcaox *= -1;
        }

        posY += direcaoY * 5;

        if (posY + 50 / 2 >= alturaY || posY <= 0) {
            direcaoY *= -1;
        }

*/

        //Triangulo tri = new Triangulo(gl);


/*
        gl.glLoadIdentity();
        gl.glTranslatef(posX, posY, 0);
        gl.glRotatef(angulo, 0, 0, 1);
        angulo += 5;
*/

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
}
