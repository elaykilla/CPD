/**
 *
 */
package models;

import controllers.Application;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.db.jpa.JPABase;
import play.db.jpa.Model;

/**
 * @author Sissoko && Elay
 * @date 1 mai 2013 21:25:07
 */
@MappedSuperclass
public class Standard extends Model implements Serializable {

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
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    public Visibility visibility;
    /**
     *
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    public User modifier;

    /**
     *
     */
    public Standard() {
        created = new Date();
        modified = new Date();
    }

    /*
     * (non-Javadoc)
     * 
     * @see play.db.jpa.GenericModel#save()
     */
    @Override
    public <T extends JPABase> T save() {
        modified = new Date();
        modifier = Application.connected(); 
        return super.save();
    }
}
