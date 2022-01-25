package cms.domain.company.serviceImpl;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface PDFGeneratorServiceImpl {

    void export(HttpServletResponse response, int idAgreement) throws IOException;

    String employeeFullName(int idAgreement);

}
