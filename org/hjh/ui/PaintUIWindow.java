package org.hjh.ui;

/**
 * <pre>
 * org.hjh.ui
 *   |_ PaintUIWindow
 * 
 * 1. 개요: 
 * 2. 작성일: 2017. 6. 22.
 * </pre> 
 *
 * @author : user
 * @version : 1.0
 * 
 * UI창을 생성하는 클래스입니다.
 */

import org.hjh.paint.Main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class PaintUIWindow extends Application
{
	public static void fakeMain()
	{
		launch();
	}
	
	@Override
	public void start(Stage stage) throws Exception 
	{
		Parent root = FXMLLoader.load(getClass().getResource("PaintUI.fxml"));
		
		Scene scene = new Scene(root);
		
		//UI창에서 Q를 눌러도 도형이 완성될 수 있도를 리스너를 추가합니다.
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() 
		{
			@Override
			public void handle(KeyEvent event) {
				System.out.println("Event");
				if(event.getCode() == KeyCode.Q)
				{
					Main.paint.drawer.addShape();
				}
			}
		});
		
		stage.setScene(scene);
		stage.setTitle("Paint System UI");
		
		//UI창과 Paint창이 겹치지 않도록 좌표를 설정합니다.
		stage.setX(1280);
		stage.setY(300);
		
		stage.show();
	}
	
}
