package org.hjh.shape;

/**
 * <pre>
 * org.hjh.paint
 *   |_ Line
 * 
 * 1. 개요: 
 * 2. 작성일: 2017. 6. 20.
 * </pre> 
 *
 * @author : user
 * @version : 1.0
 * 
 * 시작점과 끝점을 가지고 있는 선을 구현한 도형입니다.
 */

import java.util.List;

import com.jogamp.opengl.GL2;

import javafx.scene.paint.Color;

public class Line implements IShape
{
	public Point[] pArray = new Point[2];
	
	public Line(Point p1, Point p2)
	{
		this.pArray[0] = p1;
		this.pArray[1] = p2;
	}
	
	public Line(Coordinate c1, Coordinate c2, Color color)
	{
		this.pArray[0] = new Point(c1, color);
		this.pArray[1] = new Point(c2, color);
	}
	
	public Line(List<Point> list)
	{
		pArray = list.toArray(pArray);
	}
	
	@Override
	public void show(GL2 gl)
	{
		gl.glBegin(GL2.GL_LINES);
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
