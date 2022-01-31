package cms.api.admin.users.company;

import cms.domain.admin.serviceImpl.CompanyServiceImpl;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CompanyController {

    private final CompanyServiceImpl companyServiceImpl;

    public CompanyController(CompanyServiceImpl companyServiceImpl) {
        this.companyServiceImpl = companyServiceImpl;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/companies")
    ResponseEntity<List<CompanyReadModel>> readAllCompanies() {
        return ResponseEntity.ok(companyServiceImpl.readAll());
    }

    @GetMapping
    @RequestMapping("/companies/read")
    List<CompanyReadModel> readAllCompaniesWithNipFilter(@Param("nip") String nip, @Param("companyName") String companyName) {
        return companyServiceImpl.readAllWithNipOrCompanyName(nip, companyName);
    }

}
