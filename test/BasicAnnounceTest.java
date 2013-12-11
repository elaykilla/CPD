import models.Dendrite;
import models.User;
import models.articles.Announce;
import models.articles.Comment;

import org.junit.Before;
import org.junit.Test;

import controllers.Application;

import play.test.Fixtures;
import play.test.UnitTest;
import services.BCrypt;

/**
 * 
 */

/**
 * @author IssaCamara
 * 
 */
public class BasicAnnounceTest extends UnitTest {

	@Before
	public void setup() {
		Fixtures.deleteDatabase();
	}

	@Test
	public void allTest() {
		Dendrite d = new Dendrite();
		d.code = "Paris";
		d.named = "Dendrite_Paris";
		d.save();
		User usr = new User();
		usr.email = "isskamara@yahoo.fr";
		usr.firstName = "Issa";
		usr.lastName = "CAMARA";
		usr.password = "cpddjamma";
		usr.dendrite = d;
		usr.save();
		assertEquals(1, User.count());
		/*********************************/
		User u = new User();
        u.firstName = "Cheick Mahady";
        u.lastName = "SISSOKO";
        u.email = "mcicheick@yahoo.fr";
        u.password = Application.bcrypt.hashpw("secrert", BCrypt.gensalt());
        u.dendrite = d;
        u.save();
        /*********************************/
		Announce a = new Announce();
		a.title="Announce_Test";
		a.author=usr;
		a.content="This message does not mean anything!!!!";
		a.save();
		assertEquals(1, Announce.count());
		a.postComment(new Comment(u,"It works!!"));
		a.save();
		assertEquals(a.comments.size(),1);
		assertEquals(1, Announce.count());
		a.delete();
		assertEquals(0, Announce.count());
		assertEquals(0,Comment.count());
	}
}
