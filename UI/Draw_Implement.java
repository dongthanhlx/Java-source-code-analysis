package UI;
import java.awt.BasicStroke;
import java.awt.Graphics2D;

public class Draw_Implement {
	private Draws shapes1,shapes2;
	private Graphics2D g2d;
	public Draw_Implement(Graphics2D g2d, Draws shapes1, Draws shapes2){
		this.g2d = g2d;
		this.shapes1 = shapes1;
		this.shapes2 = shapes2;
	}
	public void Draw(){
		float[] dash = {4f, 0f, 2f};
		int marginTop = 30;
		int X = 5;
		int Y = 15;
		/*  ve  duong  thang */
		BasicStroke bs = new BasicStroke(1, BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_ROUND, 1.0f, dash, 2f);
		g2d.setStroke(bs);
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
		BasicStroke bs1 = new BasicStroke(1, BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_BEVEL);
		g2d.setStroke(bs1);
		/*	ve hinh mui ten 	*/
		g2d.drawLine(shapes2.getX()+shapes2.getWidth()/2-X, shapes2.getY()+shapes2.getHeight()+Y, shapes2.getX()+shapes2.getWidth()/2+X, shapes2.getY()+shapes2.getHeight()+Y);
		g2d.drawLine(shapes2.getX()+shapes2.getWidth()/2-X, shapes2.getY()+shapes2.getHeight()+Y, shapes2.getX()+shapes2.getWidth()/2, shapes2.getY()+shapes2.getHeight());
		g2d.drawLine(shapes2.getX()+shapes2.getWidth()/2+X, shapes2.getY()+shapes2.getHeight()+Y, shapes2.getX()+shapes2.getWidth()/2, shapes2.getY()+shapes2.getHeight());
		
	}
}

