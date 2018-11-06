
package Data;

import java.util.*;
public class Class {

	    private int acm;                                                            // 0=private, 1=package, 2=protected, 3=public
	    private boolean isInterface;                                                // Interface or normal class
	    private boolean isAbstract;                                                 // Abstract class or normal class
	    private boolean isStatic;                                                   // Static or not
	    private boolean isFinal;                                                    // Final or not
	    private String name;                                                        // Class's name
	    private String eSource;                                                     // Extends from which (source)
	    private ArrayList<String> iSources;                                         // Implements from which (source)
	    private ArrayList<Attributes> attributes;                               // Array of attributes
	    private ArrayList<Methods> methods;                                     // Array of methods

	    public Class() {
	        this.acm = 1;
	        this.isInterface = false;
	        this.isAbstract = false;
	        this.isStatic = false;
	        this.isFinal = false;
	        this.name = "";
	        this.eSource = "";
	        this.iSources = new ArrayList<>();
	        this.attributes = new ArrayList<>();
	        this.methods = new ArrayList<>();
	    }

	    /* Class */

	    public int getAcm() {
	        return acm;
	    }

	    public void setAcm(int acm) {
	        this.acm = acm;
	    }

	    public boolean getIsInterface() {
	        return isInterface;
	    }

	    public void setIsInterface(boolean isInterface) {
	        this.isInterface = isInterface;
	    }
	    
	    public boolean getIsAbstract() {
	        return isAbstract;
	    }

	    public void setIsAbstract(boolean isAbstract) {
	        this.isAbstract = isAbstract;
	    }

	    public boolean getIsStatic() {
	        return isStatic;
	    }

	    public void setIsStatic(boolean isStatic) {
	        this.isStatic = isStatic;
	    }

	    public boolean getIsFinal() {
	        return isFinal;
	    }

	    public void setIsFinal(boolean isFinal) {
	        this.isFinal = isFinal;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public boolean getIsExtend() {
	        if (eSource.equals("")) {
	            return false;
	        }
	        else {
	            return true;
	        }
	    }

	    public String getESource() {
	        return eSource;
	    }

	    public void setESource(String eSource) {
	        this.eSource = eSource;
	    }

	    public boolean getIsImplement() {
	        if (iSources.size()==0) {
	            return false;
	        }
	        else {
	            return true;
	        }
	    }

	    public ArrayList<String> getISources() {
	        return iSources;
	    }

	    public void setISources(ArrayList<String> iSource) {
	        this.iSources = iSource;
	    }

	    public void addISource(String iSource) {
	        this.iSources.add(iSource);
	    }

	    public String toString(){
	        String tempString = "";
	        switch (acm) {
	            case 0:
	                tempString += "- ";
	                break;
	            case 1:
	                tempString += "~ ";
	                break;
	            case 2:
	                tempString += "# ";
	                break;
	            case 3:
	                tempString += "+ ";
	                break;
	        }
	        if (isAbstract) {
	            tempString += "abstract ";
	        }
	        if (isStatic) {
	            tempString += "static ";
	        }
	        if (isFinal) {
	            tempString += "final ";
	        }
	        if (isInterface) {
	            tempString += "interface " + name;
	        }
	        else {
	            tempString += "class " + name;
	        }
	        if (getIsExtend()) {
	            tempString += " extends " + eSource;
	        }
	        if (getIsImplement()) {
	            tempString += " implements ";
	            if (iSources.size()==1) {
	                tempString += iSources.get(0);
	            }
	            for (int i=0; i<=iSources.size()-1; i++) {
	                if (i==iSources.size()-1) {
	                    tempString += iSources.get(i);
	                }
	                else {
	                    tempString += iSources.get(i) + ", ";
	                }
	            }
	        }
	        return tempString;
	    }

	    /* Attributes */

	    public ArrayList<Attributes> getAttributes() {
	        return attributes;
	    }

	    public void setAttributes(ArrayList<Attributes> attributes) {
	        this.attributes = attributes;
	    }

	    public void addAttribute(Attributes attrib) {
	        this.attributes.add(attrib);
	    }

	    public void showAttributes() {
	        for (int i=0; i<attributes.size(); i++){          
	            System.out.println(attributes.get(i).toString());
	        }
	    }

	    /* Methods */

	    public ArrayList<Methods> getMethods() {
	        return methods;
	    }

	    public void setMethods(ArrayList<Methods> methods) {
	        this.methods = methods;
	    }

	    public void addMethod(Methods method) {
	        this.methods.add(method);
	    }

	    public void showMethods() {
	        for (int i=0; i<methods.size(); i++){
	            System.out.println(methods.get(i).toString());
	        }
	    }

}

