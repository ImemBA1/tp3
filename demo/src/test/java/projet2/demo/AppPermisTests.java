package projet2.demo;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.context.annotation.ComponentScan;
import projet2.demo.model.Citoyen;
import projet2.demo.model.Permis;
import projet2.demo.model.User;
import projet2.demo.repository.CitoyenRepository;
import projet2.demo.repository.PermisRepository;
import projet2.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import projet2.demo.service.SystemService;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

@DataJpaTest
@ComponentScan(basePackages={"projet2.demo.service"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppPermisTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermisRepository permisRepository;

    @Autowired
    private CitoyenRepository citoyenRepository;


    @Autowired
    private SystemService systemService;


    @BeforeAll
    public void insertData(){
        Citoyen c1 = new Citoyen();
        Permis p1 = new Permis();
        p1.setTypePermis("VACCIN");
        p1.setDatePermisTest(LocalDate.now());
        p1.setDateExpirationTest(LocalDate.now().plusDays(15));
        c1.setNasm(500); c1.setNom("Amine"); c1.setPrenom("Bilwa");
        c1.setAge(22); c1.setSexe("Homme");
        c1.setLogin("Toto"); c1.setPassword("toto1234"); c1.setPermis(p1);
        c1.setEmail("toto@gmail.com"); c1.setTelephone("514558795"); c1.setVille("Ottawa");

        Citoyen c2 = new Citoyen();
        Permis p2 = new Permis();
        p2.setTypePermis("TEST");
        c2.setLogin("Tata"); c2.setPassword("tata1234"); c2.setPermis(p2);

        permisRepository.saveAll(Arrays.asList(p1,p2));
        userRepository.saveAll(Arrays.asList(c1,c2));
    }

    @Test
    public void testFindAllUsers(){
        assertEquals(userRepository.findAll().size(),2);
        assertNotNull(userRepository.findAll());
        assertEquals(userRepository.findAll().size(), 2);
        assertNotEquals(userRepository.findAll().size(), 100);
    }

    @Test
    public void testLogin(){
        assertTrue(systemService.login("Toto", "toto1234"));
        assertTrue(systemService.login("Tata", "tata1234"));
        assertFalse(systemService.login("Titi", "Titi1234"));
    }

    @Test
    public void testInscription(){
        assertTrue(systemService.inscription("toto@gmail.com",500
                , "Amine", "Bilwa"));
        assertFalse(systemService.inscription("toto@gmail.com",500
                , "Amine", "Talib"));
    }

    @Test
    public void testRenouvellement(){
        Citoyen citoyen =  citoyenRepository.findCitoyenByEmail("toto@gmail.com");
        systemService.Renouvellement(citoyen,"toto@gmail.com",
                "514558795", "Ottawa");
        assertEquals(citoyen.getPermis().getDateExpirationTest(),LocalDate.now().plusDays(15));
    }
    @Test
    public void testMdpOublie(){
        Citoyen citoyen = citoyenRepository.findCitoyenByEmail(("toto@gmail.com"));
        systemService.mdpOublie(citoyen,"toto@gmail.com",22,"Homme","tikito22");
        assertEquals(citoyen.getPassword(),"tikito22");
    }
}
