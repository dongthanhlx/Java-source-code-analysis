package UI;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class Draws {
	private Rectangle2D.Float rect;
	private Graphics2D g2d;
	private String nameClass,nameExtendsClass;
	private String[] nameAttributes, nameMethods;
	private int x,y,size,marginLeft=10,marginTop=5,width,height,marginBottom = 5,marginRight=5;
	public Draws(String nameClass, String nameExtendsClass){
		this.nameClass = nameClass;
		this.nameExtendsClass = nameExtendsClass;
	}
	public Draws(Graphics2D g2d, String nameClass, String[] nameAttributes,
			String[] nameMethods, int x, int y, int size ){
		this.g2d = g2d;
		this.nameClass = nameClass;
		this.nameAttributes = nameAttributes;
		this.nameMethods = nameMethods;
		this.x = x;
		this.y = y;
		this.size = size;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
//	/* start demo */
//	public boolean isHit(float x, float y){
//		return rect.getBounds2D().contains(x, y);
//	}
//	public void addX(float x){
//		this.x +=x;
//	}
//	public void addY(float y){
//		this.y +=y;
//	}
//	public void addWidth(float w){
//		this.width +=w;
//	}
//	public void addHeight(float h){
//		this.height +=h;
//	}
//	/* end demo */
	public void setExtends(String nameExtendsClass){
		this.nameExtendsClass = nameExtendsClass;
	}
	public String getExtends(){
		return nameExtendsClass;
	}
	public void DrawText(){
        int save = 0;
        int saveAsY = getY();
        int saveAsX = getX();
    	g2d.setFont(new Font("Courier New",Font.PLAIN, this.size));
        g2d.drawString(nameClass, saveAsX+marginLeft, saveAsY+marginTop+size);
        save = nameClass.length();
        saveAsY=y+marginTop+size+marginBottom;
        if(nameAttributes.length == 0){
        	saveAsY=saveAsY+marginTop+size+marginBottom;
        } else for(int j=0; j<nameAttributes.length; j++){
        	g2d.drawString(nameAttributes[j], saveAsX+marginLeft, saveAsY+marginTop+size);
        	if(save<nameAttributes[j].length())
        		save = nameAttributes[j].length();
        	saveAsY=saveAsY+marginTop+size+marginBottom;
        }
        if(nameMethods.length == 0)	saveAsY=saveAsY+marginTop+size+marginBottom;
        else for(int j=0; j<nameMethods.length; j++){
        	g2d.drawString(nameMethods[j], saveAsX+marginLeft, saveAsY+marginTop+size);
        	if(save<nameMethods[j].length())
        		save = nameMethods[j].length();
        	saveAsY=saveAsY+marginTop+size+marginBottom;
        }
    	
        this.width =  (marginLeft+save*(int)(((double)size)/1.54))+marginRight;
        this.height = saveAsY-getY();
        
	}
	public int getWidth(){
		return this.width;
	}
	public int getHeight(){
		return this.height;
	}
	public void DrawRect(){
		g2d.drawRect(getX(), getY(), getWidth(), getHeight());
		g2d.drawRect(getX(), getY()+marginTop+size+marginBottom, getWidth(), (marginTop+size+marginBottom)*(nameAttributes.length==0?1:nameAttributes.length));
	}

}
