package com.example.adailson.template_cg;

import javax.microedition.khronos.opengles.GL10;

public class Quadrado extends Geometria {

    public Quadrado(GL10 gl) {
        super(gl);
    }

    @Override
    public void desenha(int first, int count) {
        super.getGl().glDrawArrays(GL10.GL_TRIANGLE_STRIP, first, count);
    }
}
