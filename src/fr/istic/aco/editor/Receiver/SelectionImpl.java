package fr.istic.aco.editor.Receiver;

/**
 * Implementation de la Selection
 *@author SANOGO Kadidiatou
 *@version 1.0
 */
public class SelectionImpl implements Selection {

	private Buffer buffer;
	private int beginindex;
	private int endindex;
	//private int BUFFER_BEGIN_INDEX;
	//private int BUFFER_END_INDEX;
	
	public SelectionImpl(Buffer buffer) {
		this.beginindex = 0;
		this.endindex = 0;
	    this.buffer = buffer;
	}
	
		@Override
	public int getBeginIndex() {
		// TODO Auto-generated method stub
		return this.beginindex;
	}

	@Override
	public int getEndIndex() {
		// TODO Auto-generated method stub
		return this.endindex;
	}

	@Override
	public int getBufferBeginIndex() {
		// TODO Auto-generated method stub
		return this.buffer.getBeginIndex();
	}

	@Override
	public int getBufferEndIndex() {
		// TODO Auto-generated method stub
		return this.buffer.getEndIndex();
	}

	@Override
	public void setBeginIndex(int beginIndex)throws IndexOutOfBoundsException{
		// TODO Auto-generated method stub
		if (beginIndex < buffer.getBeginIndex() || beginIndex > buffer.getEndIndex()){
            throw new IndexOutOfBoundsException();
        }
        this.beginindex = beginIndex; 	
	}

	@Override
	public void setEndIndex(int endIndex) throws IndexOutOfBoundsException{
		// TODO Auto-generated method stub
		if (endIndex < 0 || endIndex > buffer.getEndIndex() || endIndex < buffer.getBeginIndex()){
            throw new IndexOutOfBoundsException();
        }
        this.endindex = endIndex;
    }	
}
