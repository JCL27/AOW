package UserInterface;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

//Borrar esta clase.
//Ir a Myinput processor, que llame al metodo drawLabel de UI manager mientras el boton este encima de esas coordenadas.

public class MyLabel extends Label implements Clickable {

	ArrayList<MyLabel> hidden;
	int timer;
	boolean visible;
	public MyLabel(CharSequence text, LabelStyle style) {
		super(text, style);
		this.hidden = new ArrayList<MyLabel>();
		visible = true;
	}
	
	public void setVisible(boolean visible){
		this.visible = visible;
	}
	@Override
	public void Click() {
		this.visible = false;
		for(MyLabel label : hidden){
			label.setVisible(true);
		}
	}

	@Override
	public boolean isClicked(double X, double Y) {
		if(X> this.getX() && Y>this.getY() && X<(this.getX()+this.getWidth())
				&& Y<((this.getY()+this.getWidth()))){
			return true;
		}
		return false;
	}
	
}
