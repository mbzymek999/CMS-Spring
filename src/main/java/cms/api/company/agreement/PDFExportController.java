package cms.api.company.agreement;

import cms.domain.company.serviceImpl.PDFGeneratorServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
public class PDFExportController {

    private final PDFGeneratorServiceImpl pdfGeneratorServiceImpl;

    public PDFExportController(PDFGeneratorServiceImpl pdfGeneratorServiceImpl) {
        this.pdfGeneratorServiceImpl = pdfGeneratorServiceImpl;
    }

    @GetMapping("/pdf/generate/{id}")
    public void generatePdf(HttpServletResponse response, @PathVariable int id) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Umowa_" + pdfGeneratorServiceImpl.employeeFullName(id) +
                "_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        this.pdfGeneratorServiceImpl.export(response, id);
    }
}
