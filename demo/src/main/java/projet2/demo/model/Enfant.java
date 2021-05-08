package projet2.demo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Enfant extends Citoyen{

    private int age;

    @ManyToOne
    private Citoyen citoyen;
}
