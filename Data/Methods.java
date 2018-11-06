package Data;

import java.util.*;

public class Methods{

    private int acm;                                                            // 0=private, 1=package, 2=protected, 3=public
    private boolean isConstructor;                                              // Constructor or not
    private boolean isAbstract;                                                 // Abstract or not
    private boolean isStatic;                                                   // Static or not
    private boolean isFinal;                                                    // Final or not
    private String type;                                                        // Method's return type
    private String name;                                                        // Method's name
    private ArrayList<String> ptypes;                                           // Parameter's type
    private ArrayList<String> pnames;                                           // Parameter's name

    public Methods() {
        acm = 1;
        isConstructor = false;
        isAbstract = false;
        isStatic = false;
        isFinal = false;
        type = "";
        name = "";
        ptypes = new ArrayList<>();
        pnames = new ArrayList<>();
    }

    public int getAcm() {
        return acm;
    }

    public void setAcm(int acm) {
        this.acm = acm;
    }

    public boolean getIsConstructor() {
        return isConstructor;
    }

    public void setIsConstructor(boolean isConstructor) {
        this.isConstructor = isConstructor;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getPtypes() {
        return ptypes;
    }

    public void setPtypes(ArrayList<String> ptypes) {
        this.ptypes = ptypes;
    }

    public void addPtype(String ptype) {
        this.ptypes.add(ptype);
    }

    public ArrayList<String> getPnames() {
        return pnames;
    }

    public void setPnames(ArrayList<String> pnames) {
        this.pnames = pnames;
    }

    public void addPname(String pname) {
        this.pnames.add(pname);
    }

    public String toString(){
        String tempString = "";
        if (isConstructor) {
            tempString += "CONSTRUCTOR: ";
        }
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
        if (!isConstructor) {
            tempString += name + ": " + type;
        }
        else {
            tempString += name;
        }
        if (ptypes.size()==0) {
            tempString += "()";
        }
        else if (ptypes.size()==1) {
            tempString += "(" + ptypes.get(0) + ": " + pnames.get(0) + ")";
        }
        else {
            for (int i=0; i<ptypes.size(); i++) {
                if (i==0) {
                    tempString += "(" + pnames.get(0) + ": " + ptypes.get(0) + ", ";
                }
                else if (i==ptypes.size()-1) {
                    tempString += pnames.get(i) + ": " + ptypes.get(i) + ")";
                }
                else {
                    tempString += pnames.get(i) + ": " + ptypes.get(i) + ", ";
                }
            }
        }
        return tempString;
    }

}

