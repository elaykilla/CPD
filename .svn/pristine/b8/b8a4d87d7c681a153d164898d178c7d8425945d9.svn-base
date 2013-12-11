package models.cvs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import models.Standard;
import play.data.validation.Required;

/**
 * 
 * @author Cheick Mahady Sissoko && Abdoulaye Maiga
 * @date 26 mai 2013 19:58:02
 */
@Entity
@Table(name = "T_SKILLS")
public class Skill extends Standard {

    /**
     *
     */
    @Required(message = "Domain is required for skills.")
    @Column(name = "DOMAIN")
    public String domain;
    @Column(name = "COMMENT")
    public String comment;
    @ManyToOne
    public Cv cv;

    public Skill() {
        super();
    }

    public Skill(String domain, String comment) {
        super();
        this.domain = domain;
        this.comment = comment;
    }
    
}
