/**
 *
 */
package models.cvs;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import models.Standard;

import net.sf.oval.constraint.MinSize;
import play.data.validation.MaxSize;
import play.data.validation.Required;

/**
 * @author Sissoko && Elay
 * @date 1 mai 2013 21:22:12
 */
@Entity
@Table(name = "T_FORMATIONS")
public class Formation extends Standard {

    @Required(message = "School is required please give a title")
    @Column(name = "SCHOOL", nullable = false)
    @MaxSize(value = 256)
    public String school;
    @Required(message = "Level is required please give a level")
    @Column(name = "LEVEL", nullable = false)
    @MaxSize(value = 256)
    public String level;
    @Required(message = "Domain is required please give a domain")
    @Column(name = "DOMAIN", nullable = false)
    public String domain;
    @Required(message = "Please provide the beginning date of the formation")
    @Column(name = "BEGIN_YEAR", nullable = false)
    public Integer beginYear;
    @Column(name = "END_YEAR", nullable = true)
    public Integer endYear;
    @Column(name = "ON_GOING")
    public Boolean onGoing;
    @ManyToOne
    public Cv cv;

    /**
     *
     */
    public Formation() {
        super();
    }

    /**
     *
     */
    public Formation(String level, String domain, String school) {
        super();
        this.level = level;
        this.domain = domain;
        this.school = school;
    }
}