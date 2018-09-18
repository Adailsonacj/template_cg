package com.example.adailson.template_cg;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Triangulo extends Geometria{

    FloatBuffer buffer;


    public Triangulo(GL10 gl) {
        super(gl);
    }

    @Override
    public void desenha(int first, int count) {
        super.getGl().glDrawArrays(GL10.GL_TRIANGLES, first, count);
    }
}
