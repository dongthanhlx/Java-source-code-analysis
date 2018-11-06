package Data;
import java.util.ArrayList;

public class InfoClass {
	private String nameClass, nameExtendsClass;
	private ArrayList<String> nameAttributes, nameMethods, allNameImplement;
	public InfoClass(String nameClass, String nameExtendsClass, ArrayList<String> allNameImplement
			, ArrayList<String> nameAttributes, ArrayList<String> nameMethods){
		this.nameClass = nameClass;
		this.nameExtendsClass = nameExtendsClass;
		this.allNameImplement = allNameImplement;
		this.nameAttributes = nameAttributes;
		this.nameMethods = nameMethods;
	}
	public String getNameClass(){
		return nameClass;
	}
	public String getNameExtendsClass(){
		return nameExtendsClass;
	}
	public String[] getAllNameImplement(){
		String[] _allNameImplement = allNameImplement.toArray(new String[allNameImplement.size()]);
		return _allNameImplement;
	}
	public String[] getNameAttributes(){
		String[] _nameAttributes = nameAttributes.toArray(new String[nameAttributes.size()]);
		return _nameAttributes;
	}
	public String[] getNameMethods(){
		String[] _nameMethods = nameMethods.toArray(new String[nameMethods.size()]);
		return _nameMethods;
	}
}

