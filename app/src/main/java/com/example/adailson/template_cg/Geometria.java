package com.example.adailson.template_cg;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public abstract class Geometria {

    private GL10 gl;
    private float [] pos;

    public Geometria(GL10 gl){
        this.gl = gl;
    }

    public abstract void desenha(int first, int count);

    public void setCor(float red, float green, float blue, float alpha){
        gl.glColor4f(red, green, blue, alpha);
    }

    public void setAng(float angulo, float posX, float posY, float posZ){
        gl.glRotatef(angulo, posX, posY, posZ);
    }

    public GL10 getGl() {
        return gl;
    }

    public void setGl(GL10 gl) {
        this.gl = gl;
    }

    public float[] getPos() {
        return pos;
    }

    public void setPos(float[] pos) {
        this.pos = pos;
    }
}
