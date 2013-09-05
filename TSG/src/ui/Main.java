package ui;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.lwjgl.LWJGLException;
import org.xml.sax.SAXException;

import render.TexturedImage;
import engine.GameLogic;
import engine.GameLoop;
import entity.PlayerEntity;
import entity.TestEntity;


public class Main {
	public static void main(String[]args) throws IOException, SAXException, ParserConfigurationException{
		try {
			GameLoop.init();
			GameLogic.addEntity(new PlayerEntity(""));
			GameLoop.run();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}	
	}
}