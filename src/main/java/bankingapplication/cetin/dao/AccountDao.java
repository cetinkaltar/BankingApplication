package bankingapplication.cetin.dao;

import bankingapplication.cetin.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class AccountDao {

    @Autowired
    private EntityManager entityManager;

    public AccountDao() {
    }

    public Account findById(Long id) {
        return this.entityManager.find(Account.class, id);
    }

    public List<Account> getAll() {
        Query query = entityManager.createQuery("From Account a", Account.class);
        return query.getResultList();
    }
}
