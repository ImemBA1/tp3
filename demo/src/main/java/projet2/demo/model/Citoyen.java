package projet2.demo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
public class Citoyen extends User implements Serializable {

    private int nasm;
    private String nom;
    private String prenom;
    private String sexe;
    private int age;
    private String email;
    private String telephone;
    private String ville;

    @OneToOne
    private Permis permis;

}
