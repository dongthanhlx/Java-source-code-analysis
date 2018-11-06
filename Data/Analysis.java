package Data;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Analysis {
    
    private File file;
    //private ArrayList<Class> classes;

    Class aClass = new Class();
    public Analysis(File file) {
        this.file = file;
        //this.classes = new ArrayList<>();
    }
    
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
    
    public void readFile() {
        File inputFile = this.file;
        
        String inputLine = "";
        boolean inString = false;
        try (Scanner scanner = new Scanner(inputFile)) {
            boolean comment = false;
            while (scanner.hasNext()) {
                String tempLine = scanner.nextLine();
                boolean add = !comment;
                for (int i=0; i<tempLine.length(); i++) {
                    if (i<tempLine.length()-1&&tempLine.charAt(i)=='/'&&tempLine.charAt(i+1)=='/'&&!inString) {
                        break;
                    }
                    else if (i<tempLine.length()-1&&tempLine.charAt(i)=='/'&&tempLine.charAt(i+1)=='*'&&!comment&&!inString) {
                        comment = true;
                        add = false;
                        inputLine += ' ';
                        i++;
                    }
                    else if (i<tempLine.length()-1&&tempLine.charAt(i)=='*'&&tempLine.charAt(i+1)=='/'&&!inString&&comment) {
                        comment = false;
                        i++;
                    }
                    if (tempLine.charAt(i)=='"'&&tempLine.charAt(i-1)!='\''&&!inString&&!comment) {
                        inString = true;
                        inputLine += ' ';
                    }
                    else if (tempLine.charAt(i)=='\\'&&tempLine.charAt(i+1)==' '&&inString) {
                        inputLine += '\\';
                        inputLine += '"';
                        i++;
                    }
                    else if (tempLine.charAt(i)=='\\'&&inString) {
                        inputLine += '\\';
                        i++;
                    }
                    else if (tempLine.charAt(i)=='"'&&inString) {
                        inString = false;
                    }
                    if ((tempLine.charAt(i)=='>'||tempLine.charAt(i)=='<'||tempLine.charAt(i)==']'||tempLine.charAt(i)=='['||tempLine.charAt(i)=='='||tempLine.charAt(i)==','||tempLine.charAt(i)=='{'||tempLine.charAt(i)=='}'||tempLine.charAt(i)==';'||tempLine.charAt(i)=='('||tempLine.charAt(i)==')')&&!inString) {
                        inputLine += ' ';
                    }
                    if (add) {
                        if (!inString) {
                            inputLine += tempLine.charAt(i);
                        }
                        if ((tempLine.charAt(i)=='"'||tempLine.charAt(i)=='<'||tempLine.charAt(i)=='>'||tempLine.charAt(i)==']'||tempLine.charAt(i)=='['||tempLine.charAt(i)=='='||tempLine.charAt(i)==','||tempLine.charAt(i)=='{'||tempLine.charAt(i)=='}'||tempLine.charAt(i)==';'||tempLine.charAt(i)=='('||tempLine.charAt(i)==')')&&!inString) {
                            inputLine += ' ';
                        }
                        else if (tempLine.charAt(i)==' '&&inString) {
                            inputLine += '"';
                        }
                        else if (tempLine.charAt(i)!=' '&&inString) {
                            inputLine += tempLine.charAt(i);
                        }
                    }
                    else if (!comment) {
                        add = true;
                    }
                }
            }
        }
        catch (Exception e) {};
        String[] inputWords = inputLine.split("\\s+");
        ArrayList<String> wordsAL = new ArrayList<String>(Arrays.asList(inputWords));
        for (int i=0; i<wordsAL.size(); i++) {
            if (wordsAL.get(i).equals("'\\")&&wordsAL.get(i+1).equals("'")) {
		wordsAL.set(i+1,wordsAL.get(i)+" '");
		wordsAL.remove(i);
            }
	}
	for (int i=0; i<wordsAL.size(); i++) {
            if (wordsAL.get(i).equals("<")&&wordsAL.get(i-1).equals("ArrayList")||wordsAL.get(i).equals("<")&&wordsAL.get(i-1).equals("List")) {
		while (!wordsAL.get(i+1).equals(">")) {
                    wordsAL.set(i+1,wordsAL.get(i)+wordsAL.get(i+1));
                    wordsAL.remove(i);
		}
		wordsAL.set(i+1,wordsAL.get(i)+wordsAL.get(i+1));
		wordsAL.remove(i);
            }
	}
	for (int i=0; i<wordsAL.size(); i++) {
            if (wordsAL.get(i).equals("[")&&wordsAL.get(i+1).equals("]")) {
		String handling = "";
		while (wordsAL.get(i).equals("[")||wordsAL.get(i).equals("]")) {
                    handling += wordsAL.get(i);
                    wordsAL.remove(i);
		}
		if (!wordsAL.get(i).equals("=")&&!wordsAL.get(i).equals(",")&&!wordsAL.get(i).equals(";")) {
                    wordsAL.set(i-1,wordsAL.get(i-1)+handling);
		}
                else {
                    wordsAL.set(i-2,wordsAL.get(i-2)+handling);
                    for (int j=i; !wordsAL.get(j).equals(";"); j++) {
                        if (wordsAL.get(j).equals("[")&&wordsAL.get(j+1).equals("]")) {
                            wordsAL.remove(j);
                            wordsAL.remove(j--);
                        }
			else if (wordsAL.get(j).equals("=")&&wordsAL.get(j+1).equals("{")) {
                            wordsAL.set(++j,wordsAL.get(j)+" ");
                            while (!wordsAL.get(j+1).equals("}")) {
				if (wordsAL.get(j+1).equals(",")) {
                                    wordsAL.set(j+1,wordsAL.get(j)+" , ");
                                    wordsAL.remove(j);
				}
                                else {
                                    wordsAL.set(j+1,wordsAL.get(j)+wordsAL.get(j+1));
                                    wordsAL.remove(j);
				}
                            }
                            wordsAL.set(j,wordsAL.get(j)+" ");
                            wordsAL.set(j+1,wordsAL.get(j)+wordsAL.get(j+1));
                            wordsAL.remove(j);
			}
                    }
		}
            }
	}
        String[] words = new String[wordsAL.size()];
        words = wordsAL.toArray(words);
        
        int rank = 0;
	for (int i=0; i<words.length; i++) {
            if (words[i].equals("{")) {
                rank++;
            }
            else if(words[i].equals("}")) {
                rank--;
            }
            if (words[i].equals("class")||words[i].equals("interface")) {       
                aClass.setName(words[i+1]);
                if (words[i].equals("interface")) {
                    aClass.setIsInterface(true);
                }
                int j = i-1;
                while (j>=0&&!words[j].equals("}")&&!words[j].equals(";")) {
                    switch (words[j]) {
                        case "private":
                            aClass.setAcm(0);
                            break;
                        case "protected":
                            aClass.setAcm(2);
                            break;
                        case "public":
                            aClass.setAcm(3);
                            break;
                        case "abstract":
                            aClass.setIsAbstract(true);
                            break;
                        case "static":
                            aClass.setIsStatic(true);
                            break;
                        case "final":
                            aClass.setIsFinal(true);
                            break;
                    }
                    j--;
                }
                j = i+2;
                while (!words[j].equals("{")) {
                    if (words[j].equals("extends")) {
                        aClass.setESource(words[j+1]);
                        j += 2;
                    }
                    else if (words[j].equals("implements")) {
                        j++;
                    }
                    else if (!words[j].equals(",")) {
                        aClass.addISource(words[j++]);
                    }
                    else {
                        j++;
                    }
                }
            }
            else if ((rank==2)&&(words[i].equals("{"))) {                    
                int j = i-2;
                Methods aMethod = new Methods();
                while (j>=0&&!words[j].equals("}")&&!words[j].equals(";")) {
                    switch (words[j]) {
                        case "private":
                            aMethod.setAcm(0);
                            break;
                        case "protected":
                            aMethod.setAcm(2);
                            break;
                        case "public":
                            aMethod.setAcm(3);
                            break;
                        case "abstract":
                            aMethod.setIsAbstract(true);
                            break;
                        case "static":
                            aMethod.setIsStatic(true);
                            break;
                        case "final":
                            aMethod.setIsFinal(true);
                            break;
                        case "(":
                            aMethod.setName(words[j-1]);
                            if (words[j-1].equals(aClass.getName())) {
                                aMethod.setIsConstructor(true);
                            }
                            else {
                                if (words[j-2].endsWith(">")) {
                                    aMethod.setType(words[j-3] + words[j-2]);
                                }
                                else {
                                    aMethod.setType(words[j-2]);
                                }
                            }
                            int k = j+1;
                            while (!words[k].equals(")")) {
                                if (!words[k].equals(",")) {
                                    if (words[k+1].endsWith(">")) {
                                        aMethod.addPtype(words[k] + words[k+1]);
                                        aMethod.addPname(words[k+2]);
                                        k += 3;
                                    }
                                    else {
                                        aMethod.addPtype(words[k]);
                                        aMethod.addPname(words[k+1]);
                                        k += 2;
                                    }
                                }
                                else {
                                    k++;
                                }
                            }
                            break;
                    }
                    j--;
                }
                aClass.addMethod(aMethod);
            }
            else if ((rank==1)&&(words[i].equals(";"))) {
                if (words[i-1].equals(")")) {
                    int j = i-4;
                    boolean isAttrib = false;
                    while (j>=0&&!words[j].equals("}")&&!words[j].equals(";")) {
                        if (words[j--].equals("=")) {
                            isAttrib = true;
                        }
                    }
                    j++;
                    if (isAttrib) {                                             
                        int tempAcm = 1;
                        boolean tempAbstract = false;
                        boolean tempStatic = false;
                        boolean tempFinal = false;
                        String tempType = "";
                        while (j>=0&&!words[j].equals("{")&&!words[j].equals("}")&&!words[j].equals(";")) {
                            switch (words[j]){
                                case "private":
                                    tempAcm = 0;
                                    j++;
                                    break;
                                case "protected":
                                    tempAcm = 2;
                                    j++;
                                    break;
                                case "public":
                                    tempAcm = 3;
                                    j++;
                                    break;
                                case "abstract":
                                    tempAbstract = true;
                                    j++;
                                    break;
                                case "static":
                                    tempStatic = true;
                                    j++;
                                    break;
                                case "final":
                                    tempFinal = true;
                                    j++;
                                    break;
                                default:
                                    if (!"".equals(tempType)) {
                                        if (!words[j].equals(",")) {
                                            Attributes aAttrib = new Attributes();
                                            aAttrib.setAcm(tempAcm);
                                            aAttrib.setIsAbstract(tempAbstract);
                                            aAttrib.setIsStatic(tempStatic);
                                            aAttrib.setIsFinal(tempFinal);
                                            aAttrib.setType(tempType);
                                            aAttrib.setName(words[j]);
                                            if (words[j+1].equals("=")) {
                                                if (words[j+2].equals("new")) {
                                                    if (words[j+4].equals("[")) {
                                                        if (words[j+5].equals("]")) {
                                                            aAttrib.setValue("new " + words[j+3] + "[]");
                                                            j += 6;
                                                        }
                                                        else {
                                                            aAttrib.setValue("new " + words[j+3] + "[" + words[j+4] + "]");
                                                            j += 7;
                                                        }
                                                    }
                                                    if (words[j+4].endsWith(">")) {
                                                        aAttrib.setValue("new " + words[j+3] + "<>()");
                                                        j += 7;
                                                    }
                                                    else {
                                                        aAttrib.setValue("new " + words[j+3] + "()");
                                                        j += 6;
                                                    }
                                                }
                                                else {
                                                    aAttrib.setValue(words[j+2]);
                                                    j += 3;
                                                }
                                            }
                                            else {
                                                j++;
                                            }
                                            aClass.addAttribute(aAttrib);
                                        }
                                        else {
                                            j++;
                                        }
                                    }
                                    else {
                                        if (words[j+1].equals("[")) {
                                            tempType = words[j] + "[]";
                                            j += 3;
                                        }
                                        else if (words[j+1].endsWith(">")) {
                                            tempType = words[j] + words[j+1];
                                            j += 2;
                                        }
                                        else {
                                            tempType = words[j++];
                                        }
                                    }
                                    break;
                            }
                        }
                    }
                    else {                                                      
                        Methods aMethod = new Methods();
                        while (!words[j].equals("}")&&!words[j].equals(";")) {
                            switch (words[j]) {
                                case "private":
                                    aMethod.setAcm(0);
                                    break;
                                case "protected":
                                    aMethod.setAcm(2);
                                    break;
                                case "public":
                                    aMethod.setAcm(3);
                                    break;
                                case "abstract":
                                    aMethod.setIsAbstract(true);
                                    break;
                                case "static":
                                    aMethod.setIsStatic(true);
                                    break;
                                case "final":
                                    aMethod.setIsFinal(true);
                                    break;
                                case "(":
                                    aMethod.setName(words[j-1]);
                                    if (words[j-1].equals(aClass.getName())) {
                                        aMethod.setIsConstructor(true);
                                    }
                                    else {
                                        if (words[j-2].endsWith(">")) {
                                            aMethod.setType(words[j-3] + words[j-2]);
                                        }
                                        else {
                                            aMethod.setType(words[j-2]);
                                        }
                                    }
                                    int k = j+1;
                                    while (!words[k].equals(")")) {
                                        if (!words[k].equals(",")) {
                                            if (words[k+1].endsWith(">")) {
                                                aMethod.addPtype(words[k] + words[k+1]);
                                                aMethod.addPname(words[k+2]);
                                                k += 3;
                                            }
                                            else {
                                                aMethod.addPtype(words[k]);
                                                aMethod.addPname(words[k+1]);
                                                k += 2;
                                            }
                                        }
                                        else {
                                            k++;
                                        }
                                    }
                                    break;
                            }
                            j++;
                        }
                        aClass.addMethod(aMethod);
                    }
                }
                else {                                                         
                    int j = i-1;
                    int tempAcm = 1;
                    boolean tempAbstract = false;
                    boolean tempStatic = false;
                    boolean tempFinal = false;
                    String tempType = "";
                    while (j-1>=0&&!words[j-1].equals("{")&&!words[j-1].equals("}")&&!words[j-1].equals(";")) {
                        j--;
                    }
                    while (j>=0&&!words[j].equals("{")&&!words[j].equals("}")&&!words[j].equals(";")) {
                        switch (words[j]){
                            case "private":
                                tempAcm = 0;
                                j++;
                                break;
                            case "protected":
                                tempAcm = 2;
                                j++;
                                break;
                            case "public":
                                tempAcm = 3;
                                j++;
                                break;
                            case "abstract":
                                tempAbstract = true;
                                j++;
                                break;
                            case "static":
                                tempStatic = true;
                                j++;
                                break;
                            case "final":
                                tempFinal = true;
                                j++;
                                break;
                            default:
                                if (!"".equals(tempType)) {
                                    if (!words[j].equals(",")) {
                                        Attributes aAttrib = new Attributes();
                                        aAttrib.setAcm(tempAcm);
                                        aAttrib.setIsAbstract(tempAbstract);
                                        aAttrib.setIsStatic(tempStatic);
                                        aAttrib.setIsFinal(tempFinal);
                                        aAttrib.setType(tempType);
                                        aAttrib.setName(words[j]);
                                        if (words[j+1].equals("=")) {
                                            if (words[j+2].equals("new")) {
                                                if (words[j+4].equals("[")) {
                                                    if (words[j+5].equals("]")) {
                                                        aAttrib.setValue("new " + words[j+3] + "[]");
                                                        j += 6;
                                                    }
                                                    else {
                                                        aAttrib.setValue("new " + words[j+3] + "[" + words[j+4] + "]");
                                                        j += 7;
                                                    }
                                                }
                                                if (words[j+4].endsWith(">")) {
                                                    aAttrib.setValue("new " + words[j+3] + "<>()");
                                                    j += 8;
                                                }
                                                else {
                                                    aAttrib.setValue("new " + words[j+3] + "()");
                                                    j += 6;
                                                }
                                            }
                                            else {
                                                aAttrib.setValue(words[j+2]);
                                                j += 3;
                                            }
                                        }
                                        else {
                                            j++;
                                        }
                                        aClass.addAttribute(aAttrib);
                                    }
                                    else {
                                        j++;
                                    }
                                }
                                else {
                                    if (words[j+1].equals("[")) {
                                        tempType = words[j] + "[]";
                                        j += 3;
                                    }
                                    else if (words[j+1].endsWith(">")) {
                                        tempType = words[j] + words[j+1];
                                        j += 2;
                                    }
                                    else {
                                        tempType = words[j++];
                                    }
                                }
                                break;
                        }
                    }
                }
            }
		}
    }
    public String printClass(){
    	return aClass.getName();
    }
    
    public ArrayList<String> printAttributes(){
    	ArrayList<String> s = new ArrayList<String>();
    	for(int i=0; i<aClass.getAttributes().size(); i++)
    		s.add(aClass.getAttributes().get(i).toString());
    	return s;
    }
    public ArrayList<String> printMethods(){
    	ArrayList<String> s = new ArrayList<String>();
    	for(int i=0; i<aClass.getMethods().size(); i++)
    		s.add(aClass.getMethods().get(i).toString());
    	return s;
    }
    public String printExtends(){
    	return aClass.getESource();
    }
    public ArrayList<String> printImplement(){
    	return aClass.getISources();
    }
    
}


