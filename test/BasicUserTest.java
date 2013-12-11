
import controllers.Application;
import java.util.List;

import models.Dendrite;
import models.Right;
import models.Role;
import models.User;
import models.Visibility;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;
import services.BCrypt;
import models.RightEnum;
import models.RoleEnum;

public class BasicUserTest extends UnitTest {

    @Before
    public void setup() {
        Fixtures.deleteDatabase();
    }

    @Test
    public void createAndFindUser() {
        Dendrite d = new Dendrite();
        d.code = "Dendrite_valence";
        d.named = "Valence";
        d.save();
        User u = new User();
        u.firstName = "Cheick Mahady";
        u.lastName = "SISSOKO";
        u.email = "mcicheick@yahoo.fr";
        u.password = Application.bcrypt.hashpw("secrert", BCrypt.gensalt());
        u.dendrite = d;
        u.save();
        User uf = User.findByEmail("mcicheick@yahoo.fr");
        assertNotNull(uf);
        Role.findOrCreate(RoleEnum.PRESIDENT.name());
        uf.attributeRole(RoleEnum.PRESIDENT.name());
        assertEquals(Role.count(), 1);
        Role.attributeRuleToRole(RoleEnum.PRESIDENT.name(),
                RightEnum.CREATE_USER.name(), Visibility.NOT_RESTRICTED);
        assertTrue(uf.canActe("Dendrite_valence", RightEnum.CREATE_USER.name()));
        assertTrue(uf.canActe("Dendrite_grenoble", RightEnum.CREATE_USER.name()));
        assertEquals(Right.count(), 1);
        assertTrue(uf.contains(RightEnum.CREATE_USER.name()));
        Role.attributeRuleToRole(RoleEnum.PRESIDENT.name(),
                RightEnum.CREATE_USER.name(), Visibility.RESTRICTED);
        assertFalse(uf.canActe("Dendrite_grenoble", RightEnum.CREATE_USER.name()));
        assertTrue(uf.canActe("Dendrite_valence", RightEnum.CREATE_USER.name()));
        uf.removeRole(RoleEnum.PRESIDENT.name());
        assertFalse(uf.contains(RightEnum.CREATE_USER.name()));
        assertFalse(uf.canActe("Dendrite_valence", RightEnum.CREATE_USER.name()));
    }

    @Test
    public void findRoleAndCRole() {
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
            u.password = "secret";
            u.dendrite = d;
            u.save();
        }
        assertEquals(10, User.count());
        RoleEnum[] renum = RoleEnum.values();
        for (int i = 0; i < renum.length; i++) {
            Role.findOrCreate(renum[i].name());
        }

        RightEnum[] ruenum = RightEnum.values();
        for (int i = 0; i < ruenum.length; i++) {
            Right r = Right.findOrCreate(ruenum[i].name());
            assertNotNull(r);
        }

        User u = User.findByEmail("mcicheick0@yahoo.fr");
        assertNotNull(u);
        u.attributeRole(RoleEnum.SUPER_USER.name());
        
        List<Role> ros = u.roles;
        List<Role> rocs = u.complementRole();
        for (Role r : ros) {
            assertFalse(rocs.contains(r));
        }
    }
}
