/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models.elections;

import java.io.Serializable;

/**
 *
 * @author Sissoko
 */
public class MandateId implements Serializable{
    long user_id;
//    long poste_id;
    long dendrite_id;
    long election_id;

    public MandateId(long user_id, /*long poste_id,*/ long dendrite_id, long election_id) {
        this.user_id = user_id;
//        this.poste_id = poste_id;
        this.dendrite_id = dendrite_id;
        this.election_id = election_id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (int) (this.user_id ^ (this.user_id >>> 32));
//        hash = 97 * hash + (int) (this.poste_id ^ (this.poste_id >>> 32));
        hash = 97 * hash + (int) (this.dendrite_id ^ (this.dendrite_id >>> 32));
        hash = 97 * hash + (int) (this.election_id ^ (this.election_id >>> 32));
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
        final MandateId other = (MandateId) obj;
        if (this.user_id != other.user_id) {
            return false;
        }
//        if (this.poste_id != other.poste_id) {
//            return false;
//        }
        if (this.dendrite_id != other.dendrite_id) {
            return false;
        }
        if (this.election_id != other.election_id) {
            return false;
        }
        return true;
    }
    
}
