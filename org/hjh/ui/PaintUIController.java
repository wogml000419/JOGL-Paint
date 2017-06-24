package org.hjh.ui;

/**
 * <pre>
 * org.hjh.ui
 *   |_ PaintUIController
 * 
 * 1. 개요: 
 * 2. 작성일: 2017. 6. 22.
 * </pre> 
 *
 * @author : user
 * @version : 1.0
 * 
 * UI의 이벤트들을 컨트롤하는 클래스입니다.
 */

import java.net.URL;
import java.util.ResourceBundle;

import org.hjh.paint.Main;
import org.hjh.paint.ShapeDrawer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;

public class PaintUIController implements Initializable
{
	public ShapeDrawer drawer = Main.paint.drawer;
	@FXML public ColorPicker colorPicker;
	@FXML public ColorPicker bgColorPicker;
	@FXML public CheckBox fillShape;
	@FXML public CheckBox useMagnet;
	@FXML public Slider magnetSensitive;
	@FXML public Label sensitiveText;
	@FXML public ListView<String> messageList;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		colorPicker.setValue(drawer.selectedColor);
		bgColorPicker.setValue(drawer.backgroundColor);
		fillShape.setSelected(drawer.fillShape);
		useMagnet.setSelected(drawer.useMagnet);
		magnetSensitive.setMin(0.005);
		magnetSensitive.setMax(0.2);
		magnetSensitive.setValue(drawer.magnetSensitive);
		sensitiveText.setText(String.format("%.3f", drawer.magnetSensitive));
	
		//UI의 magnetSensitive 값이 바뀔 때마다 변화를 반영할 수 있도록 리스너를 추가합니다.
		magnetSensitive.valueProperty().addListener(new ChangeListener<Number>()
				{
					@Override
					public void changed(ObservableValue<? extends Number> observable, Number oldValue,
							Number newValue) 
					{
						drawer.magnetSensitive = newValue.doubleValue();
						drawer.magnetSensitivePow = Math.pow(drawer.magnetSensitive, 2);
						sensitiveText.setText(String.format("%.3f", drawer.magnetSensitive));
					}
				});
		
		Main.messages = messageList.getItems();
	}
	
	@FXML 
	public void handleClearAction(ActionEvent event)
	{
		Main.paint.shapes.clear();
		drawer.points.clear();
	}
	
	@FXML
	public void handleUndoAction(ActionEvent event)
	{
		drawer.Undo();
		colorPicker.setValue(drawer.selectedColor);
	}
	
	@FXML
	public void handleColorChange(ActionEvent event)
	{
		Main.messages.add("Current color changed to " + colorPicker.getValue().toString());
		drawer.selectedColor = colorPicker.getValue();
	}
	
	@FXML
	public void handleBgColorChange(ActionEvent event)
	{
		Main.messages.add("Background color changed to " + bgColorPicker.getValue().toString());
		drawer.backgroundColor = bgColorPicker.getValue();
	}
	
	@FXML
	public void handleFillChange(ActionEvent event)
	{
		drawer.fillShape = fillShape.isSelected();
	}
	
	@FXML
	public void handleUseMagnetChange(ActionEvent event)
	{
		drawer.useMagnet = useMagnet.isSelected();
	}
}
