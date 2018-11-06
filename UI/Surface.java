package UI;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;

import Data.Analysis;
import Data.FileList;
import Data.InfoClass;

public class Surface extends JPanel {
	public Surface(String path, int size){
		this.path = path;
		this.size = size;
	}
	static String path;
	private int  size;
	static int sizeFile;
    public void doDrawing(Graphics g, int size) {
    	FileList list = new FileList(this.path);
    	sizeFile = list.getList().size();
    	String[] allNameClass = new String[sizeFile];
    	String[] _nameAttributes = new String[sizeFile];
    	String[] _nameMethods = new String[sizeFile];
        String nameClass = new String();
        
        Draws[] drawShapes = new Draws[sizeFile];
        InfoClass[] infoClass = new InfoClass[sizeFile];
        Draw_Is_A drawIsA ;
        Draw_Implement drawImplement;
        Graphics2D g2d = (Graphics2D) g;
        int x1=50,y1=50,a=100;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        		RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, 
        		RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        int[] s = new int[sizeFile];	
        int[] x = new int[sizeFile];
        int[] Extends = new int[sizeFile];
        int[][] Implement = new int[sizeFile][sizeFile];
        for(int i=0; i<sizeFile; i++)	Extends[i]=-1;
        for(int i=0; i<sizeFile; i++){
        	for(int j=0; j<sizeFile; j++)
        		Implement[i][j] = -1;
        }
        
        for(int i=0; i<sizeFile; i++){
        	Analysis file = new Analysis(list.getList().get(i));
        	file.readFile();
        	allNameClass[i] = file.printClass();
        	infoClass[i] = new InfoClass(file.printClass(),file.printExtends(), file.printImplement()
        			,file.printAttributes(), file.printMethods());
        }
        
        for(int i=0; i<sizeFile; i++){
        	int sizeName = infoClass[i].getAllNameImplement().length;
        	if(size > 0){
        		for(int j=0; j<sizeName; j++){
        			for(int l=0; l<sizeFile; l++){
        				if(infoClass[i].getAllNameImplement()[j].equals(allNameClass[l])){
        					Implement[i][j] = l;
        					s[l]=s[l]>s[i]+1?s[l]:s[i]+1;
        					break;
        				}
        			}
        		}
        	}
        }
        for(int i=0; i<sizeFile; i++){
        	String nameExtend = infoClass[i].getNameExtendsClass();
        	if(!nameExtend.equals("")){
        		for(int j=0; j<sizeFile; j++){
        			if(nameExtend.equals(allNameClass[j])){
        				Extends[i] =  j;
        				s[j] = s[j]>s[i+1]?s[j]:s[i]+1;
        				break;
        			}
        		}
        	}
        }
        for(int i=0; i<sizeFile; i++)	x[s[i]]++;
        int maxHeight = 0;
    	
        for(int i=sizeFile-1; i>=0; i--){
        	int saveAsX = x1;
        	int saveAsY = y1;
        	if(x[i]>0){		// neu .s[i] xuat hien > 0
        		for(int j=1; j<=x[i]; j++){
        			for(int m=0; m<sizeFile; m++){
        				if(s[m] == i)	{
        					nameClass = infoClass[m].getNameClass();
        					_nameAttributes = infoClass[m].getNameAttributes();
        					_nameMethods = infoClass[m].getNameMethods();
        					drawShapes[m] = new Draws(g2d, nameClass, _nameAttributes, _nameMethods, x1, y1, size);
        		        	drawShapes[m].DrawText();
        		        	drawShapes[m].DrawRect();
        		        	maxHeight = drawShapes[m].getHeight()>maxHeight ? drawShapes[m].getHeight():maxHeight;
        		        	x1 = drawShapes[m].getX()+drawShapes[m].getWidth()+a;
        		        	j++;
        				}
        			}
        		}
            	y1 = y1+maxHeight+a;
            	x1 = saveAsX;
        	}
        }
        for(int i=0; i<sizeFile; i++){
        	if(Extends[i] >= 0 ){
        		drawIsA = new Draw_Is_A(g2d, drawShapes[i], drawShapes[Extends[i]]);
                drawIsA.Draw();
        	}
        }
        for(int i=0; i<sizeFile; i++){
        	if(Implement[i].length > 0)
            	for(int j=0; j<Implement[i].length; j++){
            		if(Implement[i][j] >= 0){
            			drawImplement = new Draw_Implement(g2d, drawShapes[i], drawShapes[Implement[i][j]]);
                		drawImplement.Draw();
            		}
            	}
        }
    }
    public int sizeFile1(){
    	return sizeFile;
    }
	//static String[] allNameClass = new String[sizeFile];
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g, size);
    }
//    public String[] AllNameFile(){
//    	return allNameClass;
//    }
}
