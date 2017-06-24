package org.hjh.paint;

/**
 * <pre>
 * org.hjh.paint
 *   |_ Main
 * 
 * 1. 개요: 
 * 2. 작성일: 2017. 6. 18.
 * </pre> 
 *
 * @author : user
 * @version : 1.0
 * 
 * 프로그램을 실행하고, Paint 창과 UI 창을 생성할 클래스입니다.
 */

import java.util.List;

import javax.swing.JFrame;

import org.hjh.ui.PaintUIWindow;

import javafx.application.Platform;


public class Main 
{
	/**
	 * frame: Paint 창입니다.
	 * paint: Paint 창 안에서 도형들을 그릴 창입니다.
	 * width: frame의 너비입니다.
	 * height: frame의 높이입니다.
	 * messages: 시스템에서 UI의 System Messages 창에 출력하는 메시지들을 담는 리스트입니다.
	 */
	public static final JFrame frame = new JFrame("OpenGL Paint");
	public static final Paint paint = new Paint();
	public static final int width = 600;
	public static final int height = 600;
	public static List<String> messages;
	
	public static void main(String[] args)
	{		
		//frame을 초기화합니다.
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(true);
		frame.pack();
		frame.addKeyListener(paint.drawer);
		
		//UI창을 생성합니다.
		PaintUIWindow.fakeMain();
	}
	
	public static void addMessage(String message)
	{
		//UI가 돌아가는 스레드에서 messages.add()를 호출합니다.
	    Platform.runLater(new Runnable()
		{
			@Override
			public void run() 
			{
				messages.add(message);
			}

		});
	}
}
