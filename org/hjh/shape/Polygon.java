package org.hjh.shape;

/**
 * <pre>
 * org.hjh.paint
 *   |_ Polygon
 * 
 * 1. 개요: 
 * 2. 작성일: 2017. 6. 22.
 * </pre> 
 *
 * @author : user
 * @version : 1.0
 * 
 * 5개 이상의 점을 가지고 있는 면을 구현한 도형입니다.
 */

import java.util.List;

import com.jogamp.opengl.GL2;

public class Polygon implements IShape
{
	public Point[] pArray = new Point[5];
	
	public Polygon(List<Point> list)
	{
		pArray = list.toArray(pArray);
	}
	
	@Override
	public void show(GL2 gl) {
		gl.glBegin(GL2.GL_POLYGON);
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
