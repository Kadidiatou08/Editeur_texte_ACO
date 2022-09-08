package fr.istic.aco.editor.Receiver;

/**
 * Classe qui represente le Buffer
 */
public class Buffer {

    private StringBuffer content;
    private int beginIndex;
    private int endIndex;

    public Buffer(){
        this.content = new StringBuffer();
        this.beginIndex = 0;
        this.endIndex = 0;
    }

    public Buffer(Buffer otherBuffer){
        this.content = new StringBuffer(otherBuffer.content);
        this.beginIndex = otherBuffer.beginIndex;
        this.endIndex = otherBuffer.endIndex;
    }
    
    public void setContent(String c, int beginIndex){

        
        if (content != null) {
            content.insert(beginIndex, c);
            this.endIndex = this.content.length();
        }

    }

    public void deleteSelection(int beginIndex, int endIndex){

        if (this.content != null) {
            this.content.delete(beginIndex, endIndex);
            this.endIndex = content.length();
        }
    }

    public String getContent(){
        return this.content.toString();
    }

    public int getBeginIndex() {
        return beginIndex;
    }

    public void setBeginIndex(int beginIndex) {
        this.beginIndex = beginIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }  		

    public void setEndIndex(int endIndex) {
      this.endIndex = endIndex;
    }

    @SuppressWarnings("unlikely-arg-type")
	public boolean isEmpty(){
        return this.content.equals("");
    }
}
