package UI;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class TreeNodeFile extends JPanel{
	private String[] nameClass;
	private String path;
	public TreeNodeFile(String path, String[] nameClass){
		this.path = path;
		this.nameClass = nameClass;
	}
	public String getPath(){
		return path;
	}
	public String[] getNameClass(){
		return nameClass;
	}
	public JTree printTree(){
		DefaultMutableTreeNode path = new DefaultMutableTreeNode(getPath());
		DefaultMutableTreeNode[] name = new DefaultMutableTreeNode[getNameClass().length];
		for(int i=0; i<getNameClass().length; i++){
			name[i] = new DefaultMutableTreeNode(getNameClass()[i]);
			path.add(name[i]);
		}
		JTree jt = new JTree(path);
		return jt;
	}
}
