package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static final SessionFactory sessionFactory = Util.getUtil().getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String hql="CREATE TABLE if not exists`users`(" +
                "`id`BIGINT NOT NULL AUTO_INCREMENT," +
                "`name`VARCHAR(50) NOT NULL," +
                "`lastname`VARCHAR(60) NOT NULL," +
                "`age`TINYINT NOT NULL," +
                "PRIMARY KEY(`id`))";
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createSQLQuery(hql).addEntity(User.class).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        String hql="DROP TABLE if exists `users`";
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction =session.beginTransaction();
        session.createSQLQuery(hql).addEntity(User.class).executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = session.load(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String hql ="from User";
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        list = session.createQuery(hql).getResultList();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.getCurrentSession();
        String hql="DELETE User";
        session.beginTransaction();
        session.createQuery(hql).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
