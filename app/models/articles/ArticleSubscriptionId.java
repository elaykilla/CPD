/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models.articles;

import java.io.Serializable;

/**
 *
 * @author Sissoko
 */
public class ArticleSubscriptionId implements Serializable{
    long article_id;
    long user_id;

    public ArticleSubscriptionId(long article_id, long user_id) {
        this.article_id = article_id;
        this.user_id = user_id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (int) (this.article_id ^ (this.article_id >>> 32));
        hash = 67 * hash + (int) (this.user_id ^ (this.user_id >>> 32));
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
        final ArticleSubscriptionId other = (ArticleSubscriptionId) obj;
        if (this.article_id != other.article_id) {
            return false;
        }
        if (this.user_id != other.user_id) {
            return false;
        }
        return true;
    }
}
