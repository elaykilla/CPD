
import controllers.Application;
import java.util.List;

import models.Dendrite;
import models.Right;
import models.Role;
import models.User;
import models.articles.Announce;
import models.articles.Article;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;
import services.BCrypt;
import models.RightEnum;
import models.RoleEnum;

public class BasicTest extends UnitTest {

    @Before
    public void setup() {
        Fixtures.deleteDatabase();
    }

    @Test
    public void allTest() {
        for (int i = 0; i < 5; i++) {
            Dendrite d = new Dendrite();
            d.code = "Dendrite_valence" + i;
            d.named = "Valence" + i;
            d.save();
        }
        assertEquals(5, Dendrite.count());
        for (int i = 0; i < 10; i++) {
            Dendrite d = Dendrite.findByCode("Dendrite_valence" + i % 5);
            User u = new User();
            u.firstName = "Cheick Mahady";
            u.lastName = "SISSOKO" + i;
            u.email = "mcicheick" + i + "@yahoo.fr";
            u.password = Application.bcrypt.hashpw("secrert", BCrypt.gensalt());
            u.dendrite = d;
            u.save();
        }
        assertEquals(10, User.count());
        for (int i = 0; i < 5; i++) {
            //List<User> ul = Dendrite.users;
            //assertEquals(2, ul.size());
        }

        for (int i = 0; i < 20; i++) {
            User u = User.findByEmail("mcicheick" + i % 5 + "@yahoo.fr");
            Article a = new Article();
            a.content = "Ceci est un article test";
            a.title = "Test" + i;
            a.author = u;
            a.save();
        }

        for (int i = 0; i < 10; i++) {
            User u = User.findByEmail("mcicheick" + i + "@yahoo.fr");
            List<Article> al = u.articles();
            if (i < 5) {
                assertEquals(4, al.size());
            } else {
                assertEquals(0, al.size());
            }
        }

        for (int i = 0; i < 20; i++) {
            Dendrite d = Dendrite.findByCode("Dendrite_valence" + i % 5);
            User u = User.findByEmail("mcicheick" + (5 + i % 5) + "@yahoo.fr");
            Announce ac = new Announce();
            ac.dendrite = d;
            ac.author = u;
            ac.title = "Une annonce" + i;
            ac.content = "Ceci est une annonce pas comme les autres donc il faut juste faire gaffe.";
            ac.save();
        }

        for (int i = 0; i < 10; i++) {
            User u = User.findByEmail("mcicheick" + i + "@yahoo.fr");
            List<Announce> al = u.announces();
            if (i >= 5) {
                assertEquals(4, al.size());
            } else {
                assertEquals(0, al.size());
            }
        }

        RoleEnum[] renum = RoleEnum.values();
        for (int i = 0; i < renum.length; i++) {
            Role r = Role.findOrCreate(renum[i].name());
            User u = User.findByEmail("mcicheick" + i % 5 + "@yahoo.fr");
            u.attributeRole(r.code);
            assertNotNull(u);
            assertNotNull(r);
        }

        User u = User.findByEmail("mcicheick" + 0 + "@yahoo.fr");
        assertNotNull(u);
        u.attributeRole(RoleEnum.SUPER_USER.name());

        Role su = Role.findByCode(RoleEnum.SUPER_USER.name());
        assertNotNull(su);

        List<User> ur = User.usersAsRole(su);
        assertFalse(ur.isEmpty());

        assertTrue(su.isAffected());

        RightEnum[] ruenum = RightEnum.values();
        for (int i = 0; i < ruenum.length; i++) {
            Right r = Right.findOrCreate(ruenum[i].name());
            assertNotNull(r);
        }
    }
}
