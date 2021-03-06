package org.semanticweb.owlapi.formats;

import java.util.List;

/**
 * Abstract implementation of OWLOntologyFormatFactory to define the use of getKey for equals and
 * hashCode.
 * 
 * @author Peter Ansell p_ansell@yahoo.com
 * 
 */
public abstract class AbstractOWLOntologyFormatFactory implements OWLOntologyFormatFactory
{
    @Override
    public int hashCode()
    {
        return this.getKey().hashCode();
    }
    
    @Override
    public boolean equals(Object other)
    {
        if(null == other)
        {
            return false;
        }
        
        if(other == this)
        {
            return true;
        }
        
        if(!(other instanceof OWLOntologyFormatFactory))
        {
            return false;
        }
        
        OWLOntologyFormatFactory otherFactory = (OWLOntologyFormatFactory)other;
        
        return this.getKey().equals(otherFactory.getKey());
    }
    
    @Override
    public final String getDefaultMIMEType()
    {
        if(getMIMETypes() == null || getMIMETypes().isEmpty())
        {
            return null;
        }
        else
        {
            return getMIMETypes().get(0);
        }
    }
    
    @Override
    public final boolean handlesMimeType(String mimeType)
    {
        if(mimeType == null)
        {
            return false;
        }
        
        String type = mimeType;
        
        if(mimeType.indexOf(';') > 0)
        {
            type = mimeType.substring(0, mimeType.indexOf(';'));
        }
        
        List<String> mimeTypes = getMIMETypes();
        
        if(mimeTypes != null)
        {
            for(String nextMimeType : mimeTypes)
            {
                if(mimeType.equalsIgnoreCase(nextMimeType))
                {
                    return true;
                }
                
                if(mimeType != type && type.equalsIgnoreCase(nextMimeType))
                {
                    return true;
                }
            }
        }
        
        return false;
    }

    /**
     * Default implementation for isTextual as the vast majority of formats will be textual.
     * 
     * Override to return false if this format cannot be processed textually using java.io.Reader.
     */
    @Override
    public boolean isTextual()
    {
        return true;
    }
    
}