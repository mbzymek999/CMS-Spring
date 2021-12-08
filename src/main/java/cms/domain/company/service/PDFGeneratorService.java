package cms.domain.company.service;

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

    private final AgreementRepository agreementRepository;

    public PDFGeneratorService(AgreementRepository agreementRepository) {
        this.agreementRepository = agreementRepository;
    }

    public void export(HttpServletResponse response, int idAgreement) throws IOException {

        Agreement agreement = agreementRepository.getById(idAgreement);

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(12);
        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(10);

        Paragraph paragraph = new Paragraph("UMOWA O PRACE NA CZAS OKRESLONY", fontTitle);
            paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        Paragraph paragraph2 = new Paragraph("zawarta dnia " + agreement.getAssignedDate() + " pomiedzy:", fontParagraph);
            paragraph2.setAlignment(Paragraph.ALIGN_LEFT);

        Paragraph paragraph3 = new Paragraph(""+agreement.getCompanyAgreement().getCompanyName() + " z siedziba przy ul. " +
                agreement.getCompanyAgreement().getStreet() + " " + agreement.getCompanyAgreement().getStreetNumber() +
                "/" +agreement.getCompanyAgreement().getBuildingNumber() +
                " kod pocztowy " + agreement.getCompanyAgreement().getPostcode() + agreement.getCompanyAgreement().getCity(), fontParagraph);
            paragraph3.setAlignment(Paragraph.ALIGN_LEFT);

        Paragraph paragraph4 = new Paragraph("Nip: "+ agreement.getCompanyAgreement().getNip() + ", Regon: " +
                agreement.getCompanyAgreement().getRegon(), fontParagraph);
            paragraph4.setAlignment(Paragraph.ALIGN_LEFT);

        Paragraph paragraph5 = new Paragraph("reprezentowanym przez: "+ "przedstawiciel", fontParagraph);
            paragraph5.setAlignment(Paragraph.ALIGN_LEFT);

        Paragraph paragraph6 = new Paragraph("a " + agreement.getEmployeeAgreement().getName() + " " + agreement.getEmployeeAgreement().getLastName(),
                fontParagraph);

        paragraph6.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph paragraph7 = new Paragraph("zamieszkala/ym "+ agreement.getEmployeeAgreement().getStreet() + " " +
                agreement.getEmployeeAgreement().getStreetNumber() + " " + agreement.getEmployeeAgreement().getBuildingNumber() +
                agreement.getEmployeeAgreement().getPostcode() + " " + agreement.getEmployeeAgreement().getCity(), fontParagraph);
            paragraph7.setAlignment(Paragraph.ALIGN_LEFT);

        Paragraph paragraph8 = new Paragraph("na czas określony od "+ agreement.getDateFrom() + " do " + agreement.getDateTo() +
                ". Wynagrodzenie: " + agreement.getSalary() + "zl", fontParagraph);
            paragraph8.setAlignment(Paragraph.ALIGN_LEFT);

        paragraph.setSpacingAfter(5);
        paragraph2.setSpacingAfter(5);
        paragraph3.setSpacingAfter(5);
        paragraph4.setSpacingAfter(5);
        paragraph5.setSpacingAfter(5);
        paragraph6.setSpacingAfter(5);
        paragraph7.setSpacingAfter(5);

        document.add(paragraph);
        document.add(paragraph2);
        document.add(paragraph3);
        document.add(paragraph4);
        document.add(paragraph5);
        document.add(paragraph6);
        document.add(paragraph7);
        document.add(paragraph8);
        document.close();
    }
}
