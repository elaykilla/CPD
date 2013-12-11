/**
 *
 */
package models.articles;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import models.Standard;
import models.User;

import play.data.validation.Required;
import services.Sortable;

/**
 * @author Sissoko && Elay
 * @date 1 mai 2013 21:55:19
 */
@Entity
@Table(name = "T_COMMENTS")
public class Comment extends Standard implements Sortable{

    /**
     * comment's author
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    public User author;
    /**
     * content of comment
     */
    @Required(message = "Content is required please give a content")
    @Lob
    @Column(name = "CONTENT", nullable = false, updatable = true)
    public String content;

    /**
     *
     */
    public Comment() {
        super();
    }

    public Comment(User author, String content) {
        super();
        this.author = author;
        this.content = content;
    }

    public int compareTo(Sortable o) {
        Comment other = (Comment) o;
        if(other == null)
            return -1;
        return -created.compareTo(other.created);
    }
}
