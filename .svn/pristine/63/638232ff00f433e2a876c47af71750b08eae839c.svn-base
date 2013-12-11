package models.elections;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import play.data.validation.Required;
import javax.persistence.ManyToOne;
import models.Standard;
import models.User;

/**
 *
 * @author Elay && Sissoko
 * @date
 */
@Entity
@Table(name = "T_CANDIDATURES")
public class Candidature extends Standard {

    @Required
    @ManyToOne(cascade = CascadeType.PERSIST)
    public Election election;
    @Required
    @ManyToOne(cascade = CascadeType.PERSIST)
    public Poste poste;
    @Required(message = "USER is required")
    @ManyToOne(cascade = CascadeType.PERSIST)
    public User candidate;
    @Required(message = "Presentation is Required")
    @Lob
    @Column(name = "PRESENTATION")
    public String presentation;

    public Candidature() {
        super();
    }

    public Candidature(Election election, Poste poste, User candidate, String presentation) {
        super();
        this.election = election;
        this.poste = poste;
        this.candidate = candidate;
        this.presentation = presentation;

    }

    public List<Vote> votes() {
        List<Vote> votes = Vote.find("Select v from Vote v where v.candidature=?", this).fetch();

        return votes;
    }

    public long numberOfVotes() {
        return votes().size();
    }

    @Override
    public Candidature delete() {
        for (Vote v : votes()) {
            v.delete();
        }

        return super.delete();

    }

    public Candidature findOrCreate() {
        Candidature candidature = Candidature.find("Select distinct c from Candidature c where c.poste=? and c.candidate=? and c.election=?", poste, candidate, election).first();
        if (candidature == null) {
            save();
            return this;
        }
        return candidature;
    }
}
