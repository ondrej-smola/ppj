package javappj;

import javappj.data.Coment;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by The CAT on 23.5.2016.
 */
@Transactional
public class ComentDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }
    @SuppressWarnings("unchecked")
    public List<Coment> getComUser(String username) {
        Criteria crit = session().createCriteria(Coment.class);

        crit.createAlias("coments", "c");

        crit.add(Restrictions.eq("c.enabled", true));
        crit.add(Restrictions.eq("c.user", username));

        return crit.list();
    }
    @SuppressWarnings("unchecked")
    public List<Coment> getComImg(int imgid) {
        Criteria crit = session().createCriteria(Coment.class);

        crit.createAlias("coments", "c");

        crit.add(Restrictions.eq("c.enabled", true));
        crit.add(Restrictions.eq("c.img", imgid));

        return crit.list();
    }
}
