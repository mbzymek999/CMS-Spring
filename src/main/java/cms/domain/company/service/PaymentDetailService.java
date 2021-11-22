//package cms.domain.company.service;
//
//import cms.api.company.dto.PaymentDetailReadModel;
//import cms.config.security.services.UserDetailsImpl;
//import cms.domain.company.repository.PaymentRepository;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//h
//@Service
//public class PaymentDetailService {
//    private final PaymentRepository paymentRepository;
//
//    public PaymentDetailService(PaymentRepository paymentRepository) {
//        this.paymentRepository = paymentRepository;
//    }
//
//    public List<PaymentDetailReadModel> readAllClientPayments(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserDetailsImpl userImpl = (UserDetailsImpl)authentication.getPrincipal();
//
//        return paymentRepository.findAllByInvoice_Company_Id(userImpl.getId()).stream().map(PaymentDetailReadModel::new).collect(Collectors.toList());
//    }
//}
