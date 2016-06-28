package javappj;

/**
 * Created by The CAT on 23.5.2016.
 */

import javappj.data.Coment;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by The CAT on 23.5.2016.
 */

/**
 * Created by The CAT on 23.5.2016.
 */
@Transactional
public class ImageDao {
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }
    @SuppressWarnings("unchecked")
    public List<Coment> getImages(int id) {
        Criteria crit = session().createCriteria(Coment.class);

        crit.createAlias("images", "i");

        crit.add(Restrictions.eq("i.enabled", true));
        crit.add(Restrictions.eq("i.img_id", id));

        return crit.list();
    }

}
