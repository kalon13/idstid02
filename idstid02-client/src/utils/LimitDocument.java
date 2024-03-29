package utils;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class LimitDocument extends PlainDocument		//Per settare la lunghezza dei JTextField
{
	
    private int limit;
    
    public LimitDocument(int limit) 
    {
        super();
        setLimit(limit);  // store the limit    
    }
    
    public final int getLimit() 
    {
    	return limit;
    }
    
    public void insertString(int offset, String s, AttributeSet attributeSet) throws BadLocationException 
    {
        if(offset < limit) // if we haven't reached the limit, insert the string
        {
        	super.insertString(offset,s,attributeSet);
        } // otherwise, just lose the string
    }
    
    public final void setLimit(int newValue) 
    {
        this.limit = newValue;
    }
    
}