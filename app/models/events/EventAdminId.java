/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models.events;

import java.io.Serializable;

/**
 *
 * @author Sissoko
 */
public class EventAdminId implements Serializable{
    long user_id;
    long event_id;

    public EventAdminId(long user_id, long event_id) {
        this.user_id = user_id;
        this.event_id = event_id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + (int) (this.user_id ^ (this.user_id >>> 32));
        hash = 23 * hash + (int) (this.event_id ^ (this.event_id >>> 32));
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
        final EventAdminId other = (EventAdminId) obj;
        if (this.user_id != other.user_id) {
            return false;
        }
        if (this.event_id != other.event_id) {
            return false;
        }
        return true;
    }
    
    
}
