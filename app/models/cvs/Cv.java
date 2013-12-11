/**
 *
 */
package models.cvs;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import models.Standard;
import models.User;

import net.sf.oval.constraint.MinSize;
import play.data.validation.MaxSize;
import play.data.validation.Required;

/**
 * @author Sissoko && Elay
 * @date 1 mai 2013 21:22:12
 */
@Entity
@Table(name = "T_CVS")
public class Cv extends Standard {

    @Required(message = "Title is required")
    @Column(name = "TITLE", nullable = true)
    @MaxSize(value = 256)
    public String title;
    @Required(message = "Please write a resume of your cv")
    @Column(name = "RESUME")
    public String resume;
    @OneToOne(cascade = CascadeType.ALL)
    public Adresse adresse;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cv")
    public List<Formation> formations;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cv")
    public List<Experience> experiences;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cv")
    public List<Skill> skills;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cv")
    public List<Hobbie> hobbies;
    @JoinTable(name = "AS_CVS_LANGUAGES", joinColumns = {
        @JoinColumn(name = "CV_ID", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "LANGUAGE_ID", referencedColumnName = "id")})
    @OneToMany(cascade = CascadeType.PERSIST)
    public List<Language> languages;

    /**
     *
     */
    public Cv() {
        super();
        this.formations = new ArrayList<Formation>();
        this.experiences = new ArrayList<Experience>();
        this.skills = new ArrayList<Skill>();
        this.hobbies = new ArrayList<Hobbie>();
        this.languages = new ArrayList<Language>();
    }

    /**
     *
     * @param title
     */
    public Cv(String title) {
        this();
        this.title = title;
    }

    /**
     *
     * @param formation
     * @return
     */
    public Cv addFormation(Formation formation) {
        formation.cv = this;
        formation.save();
        return this;
    }

    /**
     *
     * @param experience
     * @return
     */
    public Cv addExperience(Experience experience) {
        experience.cv = this;
        experience.save();
        return this;
    }

    /**
     *
     * @param skill
     * @return
     */
    public Cv addSkill(Skill skill) {
        skill.cv = this;
        skill.save();
        return this;
    }

    /**
     *
     * @param hobbie
     * @return
     */
    public Cv addHobbie(Hobbie hobbie) {
        hobbie.cv = this;
        hobbie.save();
        return this;
    }

    /**
     * We search the current language in database if that exist we just return
     * it.
     *
     * @param language
     * @return
     */
    public Cv addLanguage(Language language) {
        if (languages.contains(language)) {
            int index = languages.indexOf(language);
            Language old = languages.get(index);
            boolean remove = languages.remove(language);
            if (remove) {
                if (!old.level.equals(language.level)) {
                    languages.add(language);
                } else {
                    languages.add(old);
                }
                save();
                return this;
            }
        }
        Language fromDB = Language.findOrCreate(language);
        if (languages.contains(fromDB)) {
            return this;
        }
        languages.add(fromDB);
        save();
        return this;
    }

    /**
     *
     * @param experience
     * @return
     */
    public Language removeLanguage(Language language) {
        languages.remove(language);
        save();
        return language;
    }

    /**
     *
     * @return
     */
    public User user() {
        User user = User.find("byCv", this).first();
        return user;
    }
}
