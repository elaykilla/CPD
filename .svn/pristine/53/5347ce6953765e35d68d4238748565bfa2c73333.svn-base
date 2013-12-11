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
public class EventCommentId implements Serializable{
    long event_id;
    long comment_id;

    public EventCommentId(long event_id, long comment_id) {
        this.event_id = event_id;
        this.comment_id = comment_id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (int) (this.event_id ^ (this.event_id >>> 32));
        hash = 97 * hash + (int) (this.comment_id ^ (this.comment_id >>> 32));
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
        final EventCommentId other = (EventCommentId) obj;
        if (this.event_id != other.event_id) {
            return false;
        }
        if (this.comment_id != other.comment_id) {
            return false;
        }
        return true;
    }
    
}
