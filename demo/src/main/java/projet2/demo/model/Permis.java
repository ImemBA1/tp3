package projet2.demo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@TableGenerator(name="tab", initialValue = 0, allocationSize = 50)
@Data
public class Permis {
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator = "tab")
    private Integer idPermis;

    @NotNull
    private String typePermis;

    private LocalDate datePermisVaccin;
    private LocalDate dateExpirationVaccin;

    private LocalDate datePermisTest;
    private LocalDate dateExpirationTest;


}
