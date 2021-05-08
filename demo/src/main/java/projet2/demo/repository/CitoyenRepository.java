package projet2.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet2.demo.model.Citoyen;

@Repository
public interface CitoyenRepository extends JpaRepository<Citoyen, Integer> {

    public Citoyen findCitoyenByEmail(String email);
    public Citoyen findCitoyenByEmailAndAgeAndSexe(String email, int age, String sexe);
    public Boolean existsCitoyenByEmail(String email);
    public Boolean existsCitoyenByNasm(int nasm);
    public Boolean existsCitoyenByNomAndPrenomIgnoreCase(String nom, String prenom);

    public Citoyen findCitoyenByEmailAndTelephoneAndVille(String email, String tel, String ville);
}
