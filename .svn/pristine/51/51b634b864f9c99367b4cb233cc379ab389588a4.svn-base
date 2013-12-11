/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;


public class RoleRightId implements Serializable {
    long role_id;
    long right_id;

    public RoleRightId(long role_id, long right_id) {
        this.role_id = role_id;
        this.right_id = right_id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + (int) (this.role_id ^ (this.role_id >>> 32));
        hash = 43 * hash + (int) (this.right_id ^ (this.right_id >>> 32));
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
        final RoleRightId other = (RoleRightId) obj;
        if (this.role_id != other.role_id) {
            return false;
        }
        if (this.right_id != other.right_id) {
            return false;
        }
        return true;
    }

}
