package org.hjh.shape;

/**
 * <pre>
 * org.hjh.paint
 *   |_ Rectangle
 * 
 * 1. 개요: 
 * 2. 작성일: 2017. 6. 20.
 * </pre> 
 *
 * @author : user
 * @version : 1.0
 * 
 * 4개의 점을 가진 면을 구현한 도형입니다.
 */

import java.util.List;

import com.jogamp.opengl.GL2;

import javafx.scene.paint.Color;

public class Rectangle implements IShape
{
	public Point[] pArray = new Point[4];
	
	public Rectangle(Point p1, Point p2, Point p3, Point p4)
	{
		this.pArray[0] = p1;
		this.pArray[1] = p2;
		this.pArray[2] = p3;
		this.pArray[3] = p4;
	}
	
	public Rectangle(Coordinate c1, Coordinate c2, Coordinate c3, Coordinate c4, Color color)
	{
		this.pArray[0] = new Point(c1, color);
		this.pArray[1] = new Point(c2, color);
		this.pArray[2] = new Point(c3, color);
		this.pArray[3] = new Point(c4, color);
	}
	
	public Rectangle(List<Point> list)
	{
		pArray = list.toArray(pArray);
	}
	
	@Override
	public void show(GL2 gl)
	{
		gl.glBegin(GL2.GL_QUADS);
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
