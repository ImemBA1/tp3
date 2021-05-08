package projet2.demo.model;
import lombok.Data;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String login;
    private String password;

    @OneToOne(targetEntity = Permis.class)
    @JoinColumn(name = "ID_PERMIS")
    private Permis permis;
}
