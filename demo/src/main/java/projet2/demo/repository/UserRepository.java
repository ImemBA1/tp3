package projet2.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet2.demo.model.Permis;
import projet2.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findUserByLogin(String login);

    public User findUserByLoginIgnoreCaseAndPassword(String login, String mdp);

    public User findUserByLoginIgnoreCase(String str);

    public boolean existsByLogin(String login);
}
