//package cms.config.security;
//
//import org.hibernate.HibernateException;
//import org.hibernate.engine.spi.SharedSessionContractImplementor;
//import org.hibernate.id.IdentifierGenerator;
//
//import java.io.Serializable;
//import java.util.UUID;
//
//public class GeneratorId implements IdentifierGenerator {
//
//    public static final String generatorName = "generatorId";
//
//    @Override
//    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object object) throws HibernateException {
//        return UUID.randomUUID().toString().replace("-", "");
//        // or any other logic you'd like for generating unique IDs
//    }
//}