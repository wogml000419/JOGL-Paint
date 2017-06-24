package org.hjh.paint;

/**
 * <pre>
 * org.hjh.paint
 *   |_ Paint
 * 
 * 1. 개요: 
 * 2. 작성일: 2017. 6. 18.
 * </pre> 
 *
 * @author : user
 * @version : 1.0
 * 
 * 그린 도형들을 보여줄 창입니다.
 */

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import org.hjh.shape.IShape;

public class Paint implements GLEventListener
{		
	/**
	 * canvas: 도형들이 보여지는 GLCanvas입니다.
	 * drawer: 사용자에게서 입력을 받아 도형을 그리는 객체입니다.
	 * shapes: 완성된 도형들을 담는 리스트입니다.
	 */
	public GLCanvas canvas;
	public ShapeDrawer drawer = new ShapeDrawer();
	public List<IShape> shapes = new ArrayList<>();
	
	public Paint()
	{
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);	
		
		canvas = new GLCanvas(capabilities);
		canvas.setSize(Main.width, Main.height);
		
		final Animator animator = new Animator(canvas);
		
		canvas.addGLEventListener(this);
		
		canvas.addMouseListener(drawer);
		canvas.addMouseMotionListener(drawer);
		canvas.addKeyListener(drawer);
		
		animator.start();

		//Main의 frame에 이 창을 추가합니다.
		Main.frame.getContentPane().add(canvas, BorderLayout.CENTER);
	}

	@Override
	public void init(GLAutoDrawable drawable) 
	{
		GL2 gl = drawable.getGL().getGL2();
		gl.glClearColor((float)drawer.backgroundColor.getRed(), 
				(float)drawer.backgroundColor.getGreen(), 
				(float)drawer.backgroundColor.getBlue(), 
				(float)drawer.backgroundColor.getOpacity());
	}

	@Override
	public void dispose(GLAutoDrawable drawable) { }

	@Override
	public void display(GLAutoDrawable drawable) 
	{
		GL2 gl = drawable.getGL().getGL2();
		
		//배경을 지정된 색으로 보여줍니다.
		gl.glClearColor((float)drawer.backgroundColor.getRed(), 
				(float)drawer.backgroundColor.getGreen(), 
				(float)drawer.backgroundColor.getBlue(), 
				(float)drawer.backgroundColor.getOpacity());
		
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		
		//저장된 도형들을 보여줍니다.
		for(IShape shape : shapes)
		{
			shape.show(gl);
		}
		
		//아직 그려지고 있는 도형을 보여줍니다.
		drawer.showPoints(gl);

		gl.glFlush();
	}
	
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) { }
}
