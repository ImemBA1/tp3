package projet2.demo.repository;

import projet2.demo.model.Permis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface PermisRepository extends JpaRepository<Permis, Integer> {

//    List<Permis> findByDatePermisTest();
//    List<Permis> findByDateExpirationTest();
}
