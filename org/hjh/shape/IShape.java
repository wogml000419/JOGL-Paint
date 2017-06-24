package org.hjh.shape;

/**
 * <pre>
 * org.hjh.paint
 *   |_ IShape
 * 
 * 1. 개요: 
 * 2. 작성일: 2017. 6. 20.
 * </pre> 
 *
 * @author : user
 * @version : 1.0
 * 
 * 도형을 정의하는 인터페이스입니다.
 * 모든 도형은 자신을 OpenGL 화면에 보여줄 수 있는 show(GL2 gl) 메소드와,
 * 자기 자신이 가지고 있는 Point를 배열로 리턴하는 getPoints() 메소드를 구현해야 합니다.
 */

import com.jogamp.opengl.GL2;

public interface IShape {

	public abstract void show(GL2 gl);
	
	public abstract Point[] getPoints();
}
