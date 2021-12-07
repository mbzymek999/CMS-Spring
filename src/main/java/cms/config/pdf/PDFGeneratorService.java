package cms.config.pdf;

import cms.api.company.agreement.AgreementRequest;
import cms.config.security.services.UserDetailsImpl;
import cms.domain.company.entity.Agreement;
import cms.domain.company.entity.Company;
import cms.domain.company.repository.AgreementRepository;
import cms.domain.user.entity.User;
import cms.domain.user.repository.UserRepository;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class PDFGeneratorService {

    private final UserRepository userRepository;
    private final AgreementRepository agreementRepository;

    public PDFGeneratorService(UserRepository userRepository, AgreementRepository agreementRepository) {
        this.userRepository = userRepository;
        this.agreementRepository = agreementRepository;
    }

    public void export(HttpServletResponse response) throws IOException {

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(16);

        Paragraph paragraph = new Paragraph("Umowa o prace", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);

        Paragraph paragraph2 = new Paragraph("zawarta w dniu" + agreementRepository.getById(1).getAssignedDate(), fontParagraph);
        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);


//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserDetailsImpl userImpl = (UserDetailsImpl)authentication.getPrincipal();
//        User userId = userRepository.findById(userImpl.getId()).orElse(null);
//        Company company = userId.getCompanyUser();
//        Paragraph paragraph3 = new Paragraph(""+company.getCompanyName() + company.getStreet() + company.getStreetNumber() +
//                company.getBuildingNumber() + company.getPostcode() + company.getCity() + " a " + signUpRequest.getName() + signUpRequest.getLastName(), fontParagraph);
//        paragraph3.setAlignment(Paragraph.ALIGN_LEFT);
//        Paragraph paragraph4 = new Paragraph("zamieszkała/ym "+ signUpRequest.getStreet() + signUpRequest.getStreetNumber() +
//                signUpRequest.getBuildingNumber() + signUpRequest.getPostcode() + signUpRequest.getCity(), fontParagraph);
//        paragraph4.setAlignment(Paragraph.ALIGN_LEFT);
//        Paragraph paragraph5 = new Paragraph("na okres od "+ signUpRequest.getDateFrom() + " do " + signUpRequest.getDateTo(), fontParagraph);
//        paragraph5.setAlignment(Paragraph.ALIGN_LEFT);

        document.add(paragraph);
        document.add(paragraph2);
//        document.add(paragraph3);
//        document.add(paragraph4);
//        document.add(paragraph5);
        document.close();
    }
}
