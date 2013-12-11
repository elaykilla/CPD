/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;

/**
 *
 * @author Sissoko
 */
public class ConversationId implements Serializable {
    long user_id1;
    long user_id2;

    public ConversationId(long user_id1, long user_id2) {
        this.user_id1 = user_id1;
        this.user_id2 = user_id2;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (int) (this.user_id1 ^ (this.user_id1 >>> 32));
        hash = 37 * hash + (int) (this.user_id2 ^ (this.user_id2 >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ConversationId other = (ConversationId) obj;
        if (this.user_id1 != other.user_id1) {
            return false;
        }
        if (this.user_id2 != other.user_id2) {
            return false;
        }
        return true;
    }
    
}
