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
public class ConversUserDendriteId implements Serializable {
    long user_id;
    long dendrite_id;

    public ConversUserDendriteId(long user_id, long dendrite_id) {
        this.user_id = user_id;
        this.dendrite_id = dendrite_id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (int) (this.user_id ^ (this.user_id >>> 32));
        hash = 29 * hash + (int) (this.dendrite_id ^ (this.dendrite_id >>> 32));
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
        final ConversUserDendriteId other = (ConversUserDendriteId) obj;
        if (this.user_id != other.user_id) {
            return false;
        }
        if (this.dendrite_id != other.dendrite_id) {
            return false;
        }
        return true;
    }
}
