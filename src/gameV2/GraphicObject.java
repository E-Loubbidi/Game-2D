package gameV2;


import javafx.scene.Node;

public class GraphicObject {

	protected Node corps;
	private boolean alive = true;
	
	public boolean touch(GraphicObject obj) {
		return corps.getBoundsInParent().intersects(obj.corps.getBoundsInParent());
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public boolean isDead() {
		return !alive;
	}
}