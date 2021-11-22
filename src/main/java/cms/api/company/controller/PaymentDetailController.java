//package cms.api.company.controller;
//
//import cms.api.company.dto.PaymentDetailReadModel;
//import cms.domain.company.service.PaymentDetailService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/payment/")
//public class PaymentDetailController {
//    private final PaymentDetailService paymentDetailService;
//
//    public PaymentDetailController(PaymentDetailService paymentDetailService) {
//        this.paymentDetailService = paymentDetailService;
//    }
//
//    @RequestMapping(value = "read", method = RequestMethod.GET)
//    ResponseEntity<List<PaymentDetailReadModel>> readAllClientPayments() {
//        return ResponseEntity.ok(paymentDetailService.readAllClientPayments());
//    }
//}