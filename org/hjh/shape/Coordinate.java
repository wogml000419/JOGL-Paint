package org.hjh.shape;

/**
 * <pre>
 * org.hjh.paint
 *   |_ Coordinate
 * 
 * 1. 개요: 
 * 2. 작성일: 2017. 6. 20.
 * </pre> 
 *
 * @author : user
 * @version : 1.0
 * 
 * Paint 창 내의 좌표를 표현하는 클래스입니다.
 * 좌표는 float 변수 두 개로 표현됩니다.
 */

public class Coordinate {

	public float x;
	public float y;
	
	public Coordinate(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public String toString()
	{
		return String.format("(%f, %f)", x, y);
	}
	
	public boolean equals(Coordinate other)
	{
		if(other == null)
			return false;
		if(other.x == this.x && other.y == this.y)
			return true;
		return false;
	}
}
