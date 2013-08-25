package ui;

import java.io.IOException;

import org.lwjgl.LWJGLException;

import render.TexturedImage;
import engine.GameEngine;
import entity.PlayerEntity;


public class Main {
	public static void main(String[]args) throws IOException{
		try {
			GameEngine.init();
			GameEngine.addToRenderables(new PlayerEntity("hi"));
			GameEngine.run();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}	
	}
}