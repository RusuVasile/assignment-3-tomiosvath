package com.assignment3.user;

import com.assignment3.consultation.model.Consultation;
import com.assignment3.user.model.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.time.LocalDateTime;

public class UserSpecifications {

    public static Specification<User> isAvailable(LocalDateTime date){
        return (root, query, criteriaBuilder) -> criteriaBuilder.exists(consultationSubquery(root, query, criteriaBuilder, date));
    }

    public static Subquery<Consultation> consultationSubquery(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, LocalDateTime date){
        Subquery<Consultation> consultationSubquery = query.subquery(Consultation.class);
        Root<Consultation> fromConsultation = consultationSubquery.from(Consultation.class);

        return consultationSubquery.select(fromConsultation).where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(fromConsultation.get("doctor"), root),
                        criteriaBuilder.equal(fromConsultation.get("date"), date)
                )
        );
    }
}
