package UI;
import java.awt.BasicStroke;
import java.awt.Graphics2D;

public class Draw_Is_A {
	private Graphics2D g2d;
	private Draws shapes1, shapes2;
	public Draw_Is_A(Graphics2D g2d, Draws shapes1, Draws shapes2){
		this.g2d = g2d;
		this.shapes1 = shapes1;
		this.shapes2 = shapes2;
	}
	public void Draw(){
		int marginTop = 30;
		int X = 5;
		int Y = 15;
		/*	ve duong thang	*/
		g2d.fillOval(shapes1.getX()+shapes1.getWidth()/2-3, shapes1.getY()-3, 6, 6);
		g2d.drawLine(shapes1.getX()+shapes1.getWidth()/2, shapes1.getY()
				, shapes1.getX()+shapes1.getWidth()/2, shapes1.getY()-marginTop);

		g2d.fillOval(shapes1.getX()+shapes1.getWidth()/2-3, shapes1.getY()-marginTop-3
				, 6, 6);
		
		g2d.drawLine(shapes1.getX()+shapes1.getWidth()/2, shapes1.getY()-marginTop, 
				shapes2.getX()+shapes2.getWidth()/2, shapes1.getY()-marginTop);
		g2d.fillOval(shapes2.getX()+shapes2.getWidth()/2-3, shapes1.getY()-marginTop-3
				, 6, 6);
		g2d.drawLine(shapes2.getX()+shapes2.getWidth()/2, shapes1.getY()-marginTop
				, shapes2.getX()+shapes2.getWidth()/2, shapes2.getY()+shapes2.getHeight()+Y);
		/*	ve hinh mui ten 	*/
		BasicStroke bs1 = new BasicStroke(1, BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_BEVEL);
		g2d.setStroke(bs1);
		g2d.drawLine(shapes2.getX()+shapes2.getWidth()/2-X, shapes2.getY()+shapes2.getHeight()+Y, shapes2.getX()+shapes2.getWidth()/2+X, shapes2.getY()+shapes2.getHeight()+Y);
		g2d.drawLine(shapes2.getX()+shapes2.getWidth()/2-X, shapes2.getY()+shapes2.getHeight()+Y, shapes2.getX()+shapes2.getWidth()/2, shapes2.getY()+shapes2.getHeight());
		g2d.drawLine(shapes2.getX()+shapes2.getWidth()/2+X, shapes2.getY()+shapes2.getHeight()+Y, shapes2.getX()+shapes2.getWidth()/2, shapes2.getY()+shapes2.getHeight());
		
	}
	
}

