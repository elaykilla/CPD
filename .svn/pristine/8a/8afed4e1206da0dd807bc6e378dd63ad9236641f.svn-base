package models.cvs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import models.Standard;
import play.data.validation.Required;

/**
 *
 * @author Cheick Mahady Sissoko && Abdoulaye Maiga
 * @date 26 mai 2013 19:58:10
 */
@Entity
@Table(name = "T_LANGUAGES")
public class Language extends Standard {

    /**
     *
     */
    @Required(message = "Language is required")
    @Column(name = "LANGUAGE")
    public String language;
    @Column(name = "LEVEL")
    public String level;

    public Language() {
        super();
    }

    /**
     * Find or create a language
     *
     * @param language
     * @return
     */
    public static Language findOrCreate(Language language) {
        Language fromDB = Language.find("byLanguageAndLevel", language.language, language.level).first();
        if (fromDB == null) {
            language.save();
            return language;
        }
        return fromDB;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + (this.language != null ? this.language.hashCode() : 0);
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
        final Language other = (Language) obj;
        if ((this.language == null) ? (other.language != null) : !this.language.equals(other.language)) {
            return false;
        }
        return true;
    }
}
