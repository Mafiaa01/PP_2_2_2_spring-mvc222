package web.DAO;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.Model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class UserDAOImpl implements UserDAO {
    @PersistenceContext()
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }


    @Override
    @Transactional
    public void addUser(User user) {
        getEntityManager().persist(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        getEntityManager()
                .createQuery("delete from User where id=: id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void editUser(User user) {
        getEntityManager().merge(user);
    }

    @Override
    public User getUserById(Long id) {
        return getEntityManager().find(User.class, id);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT userrr FROM User userrr", User.class).getResultList();
    }
}