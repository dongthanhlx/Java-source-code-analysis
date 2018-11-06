package Data;

public class Attributes {

    private int acm;                                                            // 0=private, 1=package, 2=protected, 3=public
    private boolean isAbstract;                                                 // Abstract or not
    private boolean isStatic;                                                   // Static or not
    private boolean isFinal;                                                    // Final or not
    private String type;                                                        // Attribute's type
    private String name;                                                        // Attribute's name
    private String keyword;                                                     // 0=none, 1=new, 2=this, 3=super
    private String value;                                                       // Attribute's value

    public Attributes() {
        acm = 1;
        isAbstract = false;
        isStatic = false;
        isFinal = false;
        type = "";
        name = "";
        keyword = "";
        value = "";
    }

    public int getAcm() {
        return acm;
    }

    public void setAcm(int acm) {
        this.acm = acm;
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

    public String getKeyword(){
        return keyword;
    }

    public void setKeyword(int keyword) {
        switch (keyword) {
            case 0:
                this.keyword = "";
                break;
            case 1:
                this.keyword = "new ";
                break;
            case 2:
                this.keyword = "this.";
                break;
            case 3:
                this.keyword = "super.";
                break;
        }
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
        tempString += name + ": " + type;
        if (!"".equals(value)) {
            tempString += " = " + value;
        }
        return tempString;
    }

}

