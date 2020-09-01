package com.app.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Audited
@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class)
public class VersionEntityManager {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

//    @Audited
//    @JsonIgnore
//    @CreatedDate
//    @Column(name = "created_date", updatable = false)
//    public Date createdDate;
//
//    @Audited
//    @JsonIgnore
//    @LastModifiedDate
//    @Column(name = "last_update")
//    public Date lastUpdate;
//
//    @Audited
//    @CreatedBy
//    @JsonIgnore
//    @Column(name = "created_by_id")
//    private Long createdById;
//
//    @Audited
//    @JsonIgnore
//    @LastModifiedBy
//    @Column(name = "updated_by_id")
//    private Long updatedById;

    @NotNull
    @Column(name = "is_active")
    public Boolean isActive = Boolean.TRUE;


}

