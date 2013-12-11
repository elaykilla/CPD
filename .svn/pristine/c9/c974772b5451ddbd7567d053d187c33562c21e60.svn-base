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
public class EventInvitationId implements Serializable{
    long guest_id;
    long event_id;

    public EventInvitationId(long guest_id, long event_id) {
        this.guest_id = guest_id;
        this.event_id = event_id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (int) (this.guest_id ^ (this.guest_id >>> 32));
        hash = 67 * hash + (int) (this.event_id ^ (this.event_id >>> 32));
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
        final EventInvitationId other = (EventInvitationId) obj;
        if (this.guest_id != other.guest_id) {
            return false;
        }
        if (this.event_id != other.event_id) {
            return false;
        }
        return true;
    }
}
