package org.hjh.shape;

/**
 * <pre>
 * org.hjh.paint
 *   |_ StripLine
 * 
 * 1. 개요: 
 * 2. 작성일: 2017. 6. 22.
 * </pre> 
 *
 * @author : user
 * @version : 1.0
 * 
 * 처음의 점과 마지막의 점이 다르고, 그 외에 하나 이상의 점이 있는 선을 구현한 도형입니다.
 */

import java.util.List;

import com.jogamp.opengl.GL2;

public class StripLine implements IShape
{
	public Point[] pArray = new Point[3];
	
	public StripLine(List<Point> list)
	{
		pArray = list.toArray(pArray);
	}
	
	@Override
	public void show(GL2 gl) {
		gl.glBegin(GL2.GL_LINE_STRIP);
		
		for(Point p : pArray)
		{
			p.show(gl);
		}
		
		gl.glEnd();
	}

	@Override
	public Point[] getPoints() {
		return pArray;
	}
	
}
