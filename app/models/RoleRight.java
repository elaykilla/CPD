/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import play.db.jpa.GenericModel;

/**
 *
 * @author Sissoko
 */
@Table(name = "AS_ROLES_RIGHTS")
@Entity
@IdClass(RoleRightId.class)
public class RoleRight extends GenericModel {

    @Id
    public long role_id;
    @Id
    public long right_id;
    
    @Column(name = "DESCRIPTION")
    public String description;
    /**
     *
     */
    @Column(name = "CREATED")
    public Date created;
    /**
     *
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFIED")
    public Date modified;
    /**
     *
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    public User modifier; 
    @ManyToOne
    @JoinColumn(name = "role_id", updatable = false, insertable = false, referencedColumnName = "id")
    public Role role;
    @ManyToOne
    @JoinColumn(name = "right_id", updatable = false, insertable = false, referencedColumnName = "id")
    public Right right;

    public RoleRight(long role_id, long right_id) {
        this.role_id = role_id;
        this.right_id = right_id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (int) (this.role_id ^ (this.role_id >>> 32));
        hash = 67 * hash + (int) (this.right_id ^ (this.right_id >>> 32));
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
        final RoleRight other = (RoleRight) obj;
        if (this.role_id != other.role_id) {
            return false;
        }
        if (this.right_id != other.right_id) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "(" + role_id + ", " + right_id + ")";
    }
}
