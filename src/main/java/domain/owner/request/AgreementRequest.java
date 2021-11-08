package domain.owner.request;

import domain.owner.dto.AgreementWriteModel;
import lombok.Data;

@Data
public class AgreementRequest {
    private AgreementWriteModel agreement;

    public AgreementWriteModel getAgreement() {
        return agreement;
    }

    public void setAgreement(AgreementWriteModel agreement) {
        this.agreement = agreement;
    }
}
