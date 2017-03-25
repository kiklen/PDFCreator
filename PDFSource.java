

package pdfmanager;


public class PDFSource {
    private String paragraph;
    private TextManager manager;
    private int pointer;
    private String phrase;
    
    public PDFSource(){
        manager= new TextManager();
//        try {
//            manager.bundle(source);
//        } catch (Exception e) {
//            System.out.println("no se encontro");
//        }
        pointer=0;
        paragraph="";
        phrase="";    
    }
    
    public String startInstruction(String paragraph){
        String instruction;
        while(paragraph.charAt(pointer)!='}'){
            pointer++;
        }
        instruction = ""+paragraph.charAt(pointer-2)+paragraph.charAt(pointer-1)
                      +paragraph.charAt(pointer);
        return instruction;
    }
    public String nextInstruction(String paragraph){
        String next="";
        while(paragraph.charAt(pointer)!='}'&&pointer<=paragraph.length()){
            pointer++;
        }
        if(paragraph.charAt(pointer-2)=='}'){
        next = ""+paragraph.charAt(pointer-2)+paragraph.charAt(pointer-1)
                      +paragraph.charAt(pointer);
        }else{
            next = ""+paragraph.charAt(pointer-3)+paragraph.charAt(pointer-2)
                      +paragraph.charAt(pointer-1)+paragraph.charAt(pointer);
        }
        return next;
    }
    public String line(String paragraph){
        
        if(pointer==paragraph.length()){
            phrase="";
        }else{
            pointer= pointer+2;
            phrase = "";
            while(paragraph.charAt(pointer) !='{'){
                phrase +=  paragraph.charAt(pointer);
                pointer++;
            }
        }
        return phrase;
    }
    public String nextLine(String paragraph){
        if(pointer>=paragraph.length()){
            phrase="";
        }else if(pointer<=paragraph.length()){
            pointer= pointer+1;
            phrase = "";
            if(pointer<=paragraph.length()){
                while(paragraph.charAt(pointer) !='{'){
                    phrase +=  paragraph.charAt(pointer);
                    pointer++;
                }
            }
            
            
        }
        return phrase;
    }
    public String endInstruction(String paragraph){
        String instruction;
        while(paragraph.charAt(pointer)!='}'){
            pointer++;
        }
        instruction= ""+paragraph.charAt(pointer-3)+paragraph.charAt(pointer-2)+
                paragraph.charAt(pointer-1)+paragraph.charAt(pointer);
        return instruction;
    }
    public boolean isThereMoreText(String paragraph){
        boolean b;
//        pointer= pointer-3;
//        String instruction= ""+paragraph.charAt(pointer-3)+
//                paragraph.charAt(pointer-2)+
//                paragraph.charAt(pointer-1)+paragraph.charAt(pointer);
        if(pointer<=(paragraph.length()-1))
            b=true;
        else
            b=false;
        return b;
    }
    public void setPointer(int i){
        pointer = i;
    }
    public void setPhrase(String s){
        phrase = "";
    }
    public int getPointer(){
        return pointer;
    }
    public String getPhrase(){
        return phrase;
    }
//    public static void main(String[] args) {
//        PDFSource source = new PDFSource("src//prueba.txt");
//        String s;
//        source.manager.readParagraph();
//        source.manager.readParagraph();
//        s= source.manager.readParagraph();
//        System.out.println(source.startInstruction(s));
//        System.out.println(source.line(s));
//        System.out.println(source.nextInstruction(s));
//        System.out.println(source.nextLine(s));
//        System.out.println(source.nextInstruction(s));
//        System.out.println(source.nextLine(s));
//        System.out.println(source.nextInstruction(s));
//        System.out.println(source.nextLine(s));
//        System.out.println(source.nextInstruction(s));
//        System.out.println(source.nextLine(s));
//        System.out.println(source.nextInstruction(s));
//        System.out.println(source.pointer);
//        System.out.println(s.length());
//    }
    
    
}
