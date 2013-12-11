/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models.articles;

import java.io.Serializable;

/**
 *
 * @author Sissoko
 */
public class AnnounceSubscriptionId implements Serializable {

    long announce_id;
    long user_id;

    public AnnounceSubscriptionId(long announce_id, long user_id) {
        this.announce_id = announce_id;
        this.user_id = user_id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (int) (this.announce_id ^ (this.announce_id >>> 32));
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
        final AnnounceSubscriptionId other = (AnnounceSubscriptionId) obj;
        if (this.announce_id != other.announce_id) {
            return false;
        }
        if (this.user_id != other.user_id) {
            return false;
        }
        return true;
    }
}
