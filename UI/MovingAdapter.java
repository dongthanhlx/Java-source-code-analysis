//package UI;
//
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//
//public class MovingAdapter extends MouseAdapter{
//	private int x;
//	private int y;
//	private Draws draw;
//	@Override
//	public void mousePressed(MouseEvent e){
//		x = e.getX();
//		y = e.getY();
//	}
//	@Override
//	public void mouseDragged(MouseEvent e){
//		doMove(e);
//	}
//	private void doMove(MouseEvent e){
//		int dx = e.getX()- x;
//		int dy = e.getY() - y;
//		if(draw.isHit(x, y)){
//			draw.addX(dx);
//			draw.addY(dy);
//		}
//		x += dx;
//		y += dy;
//	}
//}
