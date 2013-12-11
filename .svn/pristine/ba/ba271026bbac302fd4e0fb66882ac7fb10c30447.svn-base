package models.elections;

import models.*;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import models.Dendrite;
import play.data.validation.Required;
import play.data.validation.Unique;

/**
 *
 * @author Elay && Sissoko
 * @date
 */
@Entity
@Table(name = "T_VOTES")
public class Vote extends Standard {

    @Required(message = "Candidature is Required")
    @ManyToOne(cascade = CascadeType.PERSIST)
    public Candidature candidature;
    @Required(message = "Elector is Required")
    @ManyToOne
    public User elector;

    public Vote() {
        super();
    }
}
