package ui;

import java.io.IOException;

import org.lwjgl.LWJGLException;

import render.TexturedImage;

import engine.GameEngine;


public class Main {
	public static void main(String[]args) throws IOException{
		try {
			GameEngine.init();
			GameEngine.addToRenderables(new TexturedImage(100,100,"Riven.png","Riven".hashCode()));
			GameEngine.addToRenderables(new TexturedImage(200,200,"Link.png","Link".hashCode()));
			GameEngine.run();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}	
	}
}