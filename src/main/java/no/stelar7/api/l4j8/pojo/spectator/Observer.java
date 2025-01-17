package no.stelar7.api.l4j8.pojo.spectator;

import java.io.Serializable;

public class Observer implements Serializable
{
    private static final long serialVersionUID = 8943260294113294522L;
    
    private String encryptionKey;
    
    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (this.getClass() != obj.getClass())
        {
            return false;
        }
        final Observer other = (Observer) obj;
        if (this.encryptionKey == null)
        {
            return other.encryptionKey == null;
        } else
        {
            return this.encryptionKey.equals(other.encryptionKey);
        }
    }
    
    /**
     * Key used to decrypt the spectator grid game data for playback
     *
     * @return String
     */
    public String getEncryptionKey()
    {
        return this.encryptionKey;
    }
    
    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.encryptionKey == null) ? 0 : this.encryptionKey.hashCode());
        return result;
    }
    
    @Override
    public String toString()
    {
        return "Observer{" +
               "encryptionKey='" + encryptionKey + '\'' +
               '}';
    }
}
