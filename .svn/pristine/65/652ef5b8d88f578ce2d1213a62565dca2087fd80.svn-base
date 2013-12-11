package jobs;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.List;
import play.*;
import play.jobs.*;
import play.test.*;

import models.*;
import models.feedback.Feedback;

/**
 *
 * @author Elay
 */
@OnApplicationStart
public class BootStrap extends Job {

    @Override
    public void doJob() {
        //Check if the db is empty
        //Fixtures.deleteDatabase();
        if (!play.Play.runingInTestMode()) {
            if (User.count() == 0) {
                Fixtures.loadModels("models/rights.yml");
                Fixtures.loadModels("models/roles.yml");
                Fixtures.loadModels("models/postes.yml");
                Fixtures.loadModels("models/dendrites.yml");
                Fixtures.loadModels("models/photos.yml");
                Fixtures.loadModels("models/users.yml");
                Fixtures.loadModels("models/elections.yml");
                Fixtures.loadModels("models/candidatures.yml");
                Fixtures.loadModels("models/votes.yml");
                Fixtures.loadModels("models/articles.yml");
                Fixtures.loadModels("models/announces.yml");
                Fixtures.loadModels("models/comments.yml");
                Fixtures.loadModels("models/events.yml");
            }
        }
//        List<Right> rights = Right.findAll();
//        RightEnum[] res = RightEnum.values();
//        if (rights.size() != res.length) {
//
//            int count = 0;
//            for (RightEnum re : res) {
//                Right right = new Right(re.name());
//                if (!rights.contains(right)) {
//                    right.save();
//                    System.out.println(right.code);
//                    count++;
//                }
//            }
//            System.out.println("Nouveau(x) droit(s): " + count);
//        }
//        
//        List<Message> msgs = Message.find("convers is null order by created desc").fetch();
//        for(Message m : msgs) {
//            Feedback f = new Feedback();
//            f.author = m.author;
//            f.content = m.content;
//            f.subject = m.subject;
//            f.created = m.created;
//            f.modified = m.modified;
//            f.modifier = m.modifier;
//            f.save();
//            m.delete();
//        }
    }
}
