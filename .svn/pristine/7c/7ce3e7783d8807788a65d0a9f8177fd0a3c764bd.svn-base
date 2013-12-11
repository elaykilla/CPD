/**
 *
 */
package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Sissoko && Elay
 * @date 5 mai 2013 19:21:49
 */
@Entity
@Table(name = "T_VISIBILITIES")
public class Visibility extends Standard {

    /**
     * If a right has a visibility not restricted that means this right is
     * application to all dendrides.
     */
    public static final String NOT_RESTRICTED = "NOT_RESTRICTED";
    /**
     * If a right has a visibility not restricted that means this right is
     * application to a specific dendrite.
     */
    public static final String RESTRICTED = "RESTRICTED";
    /**
     *
     */
    public static final String PUBLIC = "PUBLIC";
    /**
     *
     */
    public static final String PRIVATE = "PRIVATE";
    /**
     *
     */
    @Column(name = "CODE", nullable = false, updatable = false, unique = true)
    public String code;

    /**
     *
     */
    public Visibility() {
        super();
    }

    public Visibility(String code) {
        super();
        this.code = code;
    }

    public static Visibility findByCode(String code) {
        Visibility v = Visibility.find("byCode", code).first();
        return v;
    }

    public static Visibility visibility(String code) {
        Visibility v = findByCode(code);
        if (v == null) {
            v = new Visibility(code);
            v.save();
            return v;
        }
        return v;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
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
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof Visibility)) {
            return false;
        }
        Visibility other = (Visibility) obj;
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
