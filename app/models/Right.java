/**
 *
 */
package models;

import controllers.Application;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.data.validation.Required;
import play.data.validation.Unique;
import play.db.jpa.JPABase;

/**
 *
 * @author Sissoko && Elay
 * @date 1 mai 2013 02:38:57
 */
@Entity
@Table(name = "T_RIGHTS")
public class Right extends Standard {

    /**
     * represents an attribute right to a user for example delete another user
     * or create an event, etc.
     */
    @Required(message = "Right code is required")
    @Unique(message = "This role code exists")
    @Column(name = "CODE", nullable = false, unique = true, updatable = true)
    public String code;
    /**
     * Describes a rule
     */
    @Column(name = "DESCRIPTION")
    public String description;
    
    @OneToMany(mappedBy = "right", cascade = CascadeType.ALL)
    public List<RoleRight> roles;

    /**
     * An empty rule.
     */
    public Right() {
        super();
    }

    /**
     * A rule with his code.
     *
     * @param code
     */
    public Right(String code) {
        super();
        this.code = code;
    }

    /**
     * finds the rule where code is $code.
     *
     * @param code
     * @return the rule
     */
    public static Right findByCode(String code) {
        Right right = find("byCode", code).first();
        return right;
    }

    /**
     * returns the Right with the code $code if it exists or create new Right
     * and return it.
     *
     * @param code
     * @return
     */
    public static Right findOrCreate(String code) {
        if (code == null) {
            return null;
        }
        Right right = findByCode(code);
        if (right == null) {
            right = new Right(code);
            right.save();
        }
        return right;
    }

    /**
     * set the description
     *
     * @param code
     * @param description
     */
    public static void setDescription(String code, String description) {
        Right right = findByCode(code);
        if (right != null) {
            right.description = description;
            right.save();
        }
    }

    /**
     * Find all dendrites ordered by order
     *
     * @param order
     * @return
     */
    public static List<Right> findAll(String order) {
        List<Right> rights = Right.find(
                "select distinct u from Right u order by " + order).fetch();
        return rights;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Right other = (Right) obj;
        if (code == null) {
            if (other.code != null) {
                return false;
            }
        } else if (!code.equals(other.code)) {
            return false;
        }
        return true;
    }
}
