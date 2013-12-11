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
public class AnnounceCommentId implements Serializable {

    long announce_id;
    long comment_id;

    public AnnounceCommentId(long announce_id, long comment_id) {
        this.announce_id = announce_id;
        this.comment_id = comment_id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + (int) (this.announce_id ^ (this.announce_id >>> 32));
        hash = 17 * hash + (int) (this.comment_id ^ (this.comment_id >>> 32));
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
        final AnnounceCommentId other = (AnnounceCommentId) obj;
        if (this.announce_id != other.announce_id) {
            return false;
        }
        if (this.comment_id != other.comment_id) {
            return false;
        }
        return true;
    }
}
