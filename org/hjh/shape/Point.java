package org.hjh.shape;

/**
 * <pre>
 * org.hjh.paint
 *   |_ Point
 * 
 * 1. 개요: 
 * 2. 작성일: 2017. 6. 20.
 * </pre> 
 *
 * @author : user
 * @version : 1.0
 * 
 * 점을 구현한 클래스입니다. 
 * 이 클래스는 도형이 아닙니다. 따라서, show()를 사용해서 Point 객체 홀로 Paint 화면에 보여질 수 없습니다.
 * 대신 두 개 이상의 Point 클래스로 이루어진 도형을 구현한 뒤, 그 객체의 glBegin()과 glEnd()를 포함한 show() 안에서 Point 객체의 show()를 호출해야 합니다.
 */

import com.jogamp.opengl.GL2;

import javafx.scene.paint.Color;

public class Point {

	public Coordinate coord;
	public Color color;
	
	public Point(Coordinate coord, Color color)
	{
		this.coord = coord;
		this.color = color;
	}
	
	public Point(Coordinate coord, double r, double g, double b, double a)
	{
		this(coord, new Color(r, g, b, a));
	}
	
	public Point(Coordinate coord, double r, double g, double b)
	{
		this(coord, new Color(r, g, b, 1.0f));
	}
	
	public Point(float x, float y, Color color)
	{
		this(new Coordinate(x, y), color);
	}
	
	public Point(float x, float y, double r, double g, double b, double a)
	{
		this(new Coordinate(x, y), new Color(r, g, b, a));
	}
	
	public Point(float x, float y, double r, double g, double b)
	{
		this(new Coordinate(x, y), new Color(r, g, b, 1.0f));
	}
	
	public void show(GL2 gl)
	{
		gl.glColor4d(color.getRed(), color.getGreen(), color.getBlue(), color.getOpacity());
		gl.glVertex2f(coord.x, coord.y);
	}
	
	public boolean equals(Point other)
	{
		if(other == null)
			return false;
		if(coord.equals(other.coord) && color.equals(other.color))
			return true;
		return false;
	}
	
	@Override
	public String toString()
	{
		return String.format("Coord = %s, Color = %s", coord.toString(), color.toString());
	}

}
