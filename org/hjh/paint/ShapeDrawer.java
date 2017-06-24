package org.hjh.paint;

/**
 * <pre>
 * org.hjh.paint
 *   |_ ShapeDrawer
 * 
 * 1. 개요: 
 * 2. 작성일: 2017. 6. 20.
 * </pre> 
 *
 * @author : user
 * @version : 1.0
 * 
 * 마우스와 키보드로 사용자에게서 입력을 받고,
 * 입력에 따라 도형을 화면에 그리는 역할을 합니다.
 */

import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import org.hjh.shape.Coordinate;
import org.hjh.shape.IShape;
import org.hjh.shape.Line;
import org.hjh.shape.LoopLine;
import org.hjh.shape.Point;
import org.hjh.shape.Polygon;
import org.hjh.shape.Rectangle;
import org.hjh.shape.StripLine;
import org.hjh.shape.Triangle;

import com.jogamp.opengl.GL2;

import javafx.scene.paint.Color;

public class ShapeDrawer implements MouseListener, MouseMotionListener, KeyListener
{
	/**
	 * selectedColor: UI에서 사용자가 선택한 Color 값입니다. 현재 점의 색상을 결정합니다.
	 * backgroundColor: UI에서 사용자가 선택한 Background Color 값입니다. 배경의 색상을 결정합니다.
	 * fillShape: UI에서 사용자가 체크한 Fill Inside 값입니다. true이면 면을 그리고, false이면 선을 그립니다.
	 * useMagnet: UI에서 사용자가 체크한 Enable Magnet 값입니다. true이면 현재 점이 처음 점에 가까워졌을 때 현재 점의 위치를 처음 점의 위치로 조정합니다. false이면 조정하지 않습니다.
	 * magnetSensitive: UI에서 사용자가 설정한  Magnet Sensitive 값입니다. 이 값에 따라 점을 조정하게 되는 영역의 범위가 달라집니다.
	 * points: 사용자가 그리고 있는 도형에서 현재 위치와 색깔이 확정된 점들을 담는 리스트입니다. 
	 * currentPoint: 사용자가 그리고 있는 도형에서 현재 위치와 색깔이 확정되지 않은 점을 담는 변수입니다. 
	 */
	public Color selectedColor = Color.BLACK;
	public Color backgroundColor = Color.WHITE;
	public boolean fillShape = true;
	public boolean useMagnet = true;
	public double magnetSensitive = 0.03;
	public double magnetSensitivePow = Math.pow(magnetSensitive, 2);
	public List<Point> points = new ArrayList<>();
	
	private Point currentPoint = new Point(0f, 0f, Color.WHITE);
	
	/**
	 * points에 저장된 점들과 currentPoint를 하나의 도형으로 만들어 화면에 보여줍니다.
	 */
	public void showPoints(GL2 gl)
	{
		int shapeType;
		switch(points.size())
		{
		case 0:
			shapeType = GL2.GL_POINTS;
			break;
			
		case 1:
			shapeType = GL2.GL_LINES;
			break;
			
		case 2:
			shapeType = fillShape 
				? GL2.GL_TRIANGLES : GL2.GL_LINE_STRIP;
			break;
			
		case 3:
			shapeType = fillShape
				? GL2.GL_QUADS : GL2.GL_LINE_STRIP;
			break;
			
		default:
			shapeType = fillShape
				? GL2.GL_POLYGON : GL2.GL_LINE_STRIP;
		}
		
		gl.glBegin(shapeType);
		
		for(Point p : points)
		{
			p.show(gl);
		}
		currentPoint.show(gl);
		
		gl.glEnd();
	}
	
	/**
	 * 마지막 점의 생성을 취소합니다. selectedColor는 취소된 점의 색으로 변경됩니다.
	 */
	public void Undo()
	{
		//points에 점이 하나 이상 있다면, 그 중 마지막 점의 생성을 취소합니다.
		int pointsSize = points.size();
		if(pointsSize > 0)
		{
			selectedColor = points.get(pointsSize-1).color;
			points.remove(pointsSize - 1);
			Main.addMessage("Removed a Point");
			return;
		}
		
		//points에 점이 없지만 완성된 도형이 하나 이상 있다면, 마지막 도형을 완성된 도형 목록에서 없애고, 그 도형의 마지막 점의 생성을 취소합니다. 
		int shapesSize = Main.paint.shapes.size();
		if(shapesSize > 0)
		{
			IShape shape = Main.paint.shapes.get(shapesSize-1);
			Main.paint.shapes.remove(shapesSize-1);
			
			for(Point p : shape.getPoints())
			{
				points.add(p);
			}
			pointsSize = points.size();
			selectedColor = points.get(pointsSize-1).color;
			points.remove(pointsSize-1);
			Main.addMessage("Removed a Point");
			return;
		}
		//완성된 도형 목록과 points 둘 다 비어있다면 아래의 메시지를 출력합니다.
		Main.addMessage("There is nothing to undo");
	}
	
	//frame의 좌표를 OpenGL의 좌표로 변환합니다.
	public Coordinate frameToGLCoordinate(int x, int y)
	{
		Insets inset = Main.frame.getInsets();
		int realWidth = Main.frame.getWidth() - (inset.left + inset.right);
		int realHeight = Main.frame.getHeight() - (inset.top + inset.bottom);
		
		float fx = (2.0f * x) / realWidth - 1;
		float fy = (-2.0f * y) / realHeight + 1;
		return new Coordinate(fx, fy);
	}

	/**
	 * 현재 그리고 있는 도형을 알맞은 도형 객체로 만들어 반환합니다. 
	 */
	public IShape returnShape()
	{
		points.add(currentPoint);
		Main.addMessage(String.format("Point added [%s]", currentPoint));
		
		int size = points.size();
		switch(size)
		{
		case 1:
			return null;
			
		case 2:
			return new Line(points);
			
		case 3:
			if(fillShape)
				return new Triangle(points);
			return new StripLine(points);
			
		case 4:
			if(fillShape)
				return new Rectangle(points);
			if(points.get(0).equals(points.get(3)))
			{
				points.remove(3);
				return new LoopLine(points);
			}
			return new StripLine(points);
			
		default:
			if(fillShape)
				return new Polygon(points);
			if(points.get(0).equals(points.get(size-1)))
			{
				points.remove(size-1);
				return new LoopLine(points);
			}
			return new StripLine(points);
		}
	}
	
	/**
	 * 현재 그리고 있는 도형을 완성된 도형 목록에 추가하고, points를 클리어합니다.
	 */
	public void addShape()
	{
		IShape shape = returnShape();
		points.clear();
		if(shape != null)
		{
			Main.paint.shapes.add(shape);
			Main.addMessage(String.format("New %s has added", shape.getClass().getSimpleName()));
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
	    points.add(currentPoint);
	    Main.addMessage(String.format("Point added [%s]", currentPoint));
	}
	
	@Override
	public void mouseMoved(MouseEvent e) 
	{
		Coordinate glCoord = frameToGLCoordinate(e.getX(), e.getY());
		
	    if(useMagnet && points.size() >= 2)
	    {
	    	double xDiff = points.get(0).coord.x - glCoord.x;
	    	double yDiff = points.get(0).coord.y - glCoord.y;
	    	
	    	if(magnetSensitivePow 
	    		> Math.pow(xDiff, 2) + Math.pow(yDiff, 2))
	    	{
	    		glCoord = points.get(0).coord;
	    	}
	    }
	    currentPoint = new Point(glCoord, selectedColor);
	}
	
	@Override
	public void mousePressed(MouseEvent e) { }

	@Override
	public void mouseReleased(MouseEvent e) { }

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) { }

	@Override
	public void mouseDragged(MouseEvent e) { }


	@Override
	public void keyTyped(KeyEvent e) { }

	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_Q)
		{
			addShape();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) { }
}
