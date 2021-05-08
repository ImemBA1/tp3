package projet2.demo.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.core.env.Environment;
import projet2.demo.model.Citoyen;
import projet2.demo.model.Permis;
import projet2.demo.model.User;
import projet2.demo.repository.CitoyenRepository;
import projet2.demo.repository.PermisRepository;
import projet2.demo.repository.UserRepository;

import com.itextpdf.kernel.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import javax.mail.internet.MimeMessage;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

@Service
public class SystemService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermisRepository permisRepository;

    @Autowired
    private CitoyenRepository citoyenRepository;

//    @Autowired
//    private JavaMailSender mailSender;

    @Autowired
    private Environment environment;

    public boolean inscription(String email, int nasm,String nom, String prenom){
        return citoyenRepository.existsCitoyenByEmail(email) && citoyenRepository.existsCitoyenByNasm(nasm)
                && citoyenRepository.existsCitoyenByNomAndPrenomIgnoreCase(nom,prenom);
    }


    public boolean login(String ndc, String mdp){
        return userRepository.findUserByLoginIgnoreCaseAndPassword(ndc, mdp) != null;
    }

    public List<Permis> AllPermis(){
        return permisRepository.findAll();
    }

    public void Renouvellement(Citoyen citoyen, String email, String tel, String ville){
        if(citoyen.getPermis().getTypePermis().equals(environment.getProperty("permis.test"))){
            if(citoyenRepository.findCitoyenByEmailAndTelephoneAndVille(email,tel,ville) == citoyen){
                citoyen.getPermis().setDateExpirationTest(citoyen.getPermis().getDateExpirationTest().plusDays(15));
            }
        }
    }

    public void mdpOublie(Citoyen citoyen, String email, int age, String sexe, String nouveauMdp){
        if (citoyenRepository.findCitoyenByEmailAndAgeAndSexe(email,age,sexe) == citoyen){
            citoyen.setPassword(nouveauMdp);
        }
    }

    public void genererQR(String data, String filePath) throws Exception{
        Path path = FileSystems.getDefault().getPath(filePath);
        QRCodeWriter qr = new QRCodeWriter();
        MatrixToImageWriter.writeToPath(qr.encode(data, BarcodeFormat.QR_CODE,
                Integer.parseInt(environment.getProperty("qrCode.width")),
                Integer.parseInt(environment.getProperty("qrCode.height"))), environment.getProperty("qrCode.extension"),
                path);
    }

    public void genererPDF(String filePath) throws Exception{
        PdfWriter writer = new PdfWriter(filePath);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        Image image = new Image(ImageDataFactory.create("C:/codeqr/codePermisUser1.PNG"));

        Paragraph p = new Paragraph( "Bonjour,\n")
                .add("Voici votre code QR permis de santé")
                .add(image)
                .add("La direction");
        document.add(p);
        document.close();
    }

//    public void EnvoieEmail(String mailTo, String subject, String body) throws Exception{
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//        helper.setTo(mailTo);
//        helper.setSubject(subject);
//        helper.setText(body);
//        helper.addAttachment("QR CODE", new File(("./codePermisUser1.PNG")));
//        helper.addAttachment("QR PDF", new File("./codePermisUser1.pdf"));
//
//        mailSender.send(message);
//    }
}
