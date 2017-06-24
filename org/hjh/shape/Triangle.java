package org.hjh.shape;

/**
 * <pre>
 * org.hjh.paint
 *   |_ Triangle
 * 
 * 1. 개요: 
 * 2. 작성일: 2017. 6. 20.
 * </pre> 
 *
 * @author : user
 * @version : 1.0
 * 
 * 3개의 점을 가진 면을 구현한 도형입니다.
 */

import java.util.List;

import com.jogamp.opengl.GL2;

import javafx.scene.paint.Color;

public class Triangle implements IShape
{
	public Point[] pArray = new Point[3];
	
	public Triangle(Point p1, Point p2, Point p3)
	{
		this.pArray[0] = p1;
		this.pArray[1] = p2;
		this.pArray[2] = p3;
	}
	
	public Triangle(Coordinate c1, Coordinate c2, Coordinate c3, Color color)
	{
		this.pArray[0] = new Point(c1, color);
		this.pArray[1] = new Point(c2, color);
		this.pArray[2] = new Point(c3, color);
	}
	
	public Triangle(List<Point> list)
	{
		pArray = list.toArray(pArray);
	}
	
	public void show(GL2 gl)
	{
		gl.glBegin(GL2.GL_TRIANGLES);
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
