//package com.app.config.audit;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//import org.hibernate.envers.DefaultRevisionEntity;
//import org.hibernate.envers.RevisionEntity;
//
//import javax.persistence.Entity;
//import javax.validation.constraints.NotBlank;
//
//@Data
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@EqualsAndHashCode(callSuper = true)
//@RevisionEntity(AuditRevisionListener.class)
//public class AuditRevisionEntity extends DefaultRevisionEntity {
//
//    @NotBlank
//    private String username;            // adding username in revision
//
//}
