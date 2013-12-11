/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models.events;

import models.articles.*;
import java.io.Serializable;

/**
 *
 * @author Sissoko
 */
public class EventSubscriptionId implements Serializable {

    long event_id;
    long user_id;

    public EventSubscriptionId(long event_id, long user_id) {
        this.event_id = event_id;
        this.user_id = user_id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (int) (this.event_id ^ (this.event_id >>> 32));
        hash = 67 * hash + (int) (this.user_id ^ (this.user_id >>> 32));
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
        final EventSubscriptionId other = (EventSubscriptionId) obj;
        if (this.event_id != other.event_id) {
            return false;
        }
        if (this.user_id != other.user_id) {
            return false;
        }
        return true;
    }
}
