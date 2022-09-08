package fr.istic.aco.editor.Receiver;

/**
 * Classe qui represente le Clipbord
 */
public class Clipboard {

    private String content;


    public Clipboard(){
        this.content ="";
    }

    public Clipboard(String content){
        this.content = content;
    }
    
    public void setContent(String c){
        this.content =c;
    }

   public String getContent() {
	   return this.content;
   }
}
