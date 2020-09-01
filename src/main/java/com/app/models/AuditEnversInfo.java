//package com.app.models;
//
//import com.app.listener.UserRevisionListener;
//import org.hibernate.envers.DefaultRevisionEntity;
//import org.hibernate.envers.RevisionEntity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Table;
//
//
//
//@Entity
//@RevisionEntity(UserRevisionListener.class)
//@Table(name = "audit_envers_info")
//public class AuditEnversInfo extends DefaultRevisionEntity {
//
//    private static final long serialVersionUID = -7604731515258123883L;
//
//    @Column(name = "user_id")
//    private String userId;
//
//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }
//
//}
