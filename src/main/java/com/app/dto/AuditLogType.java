package com.app.dto;

import com.app.exceptionhandler.CustomException;

import java.util.Optional;
import java.util.stream.Stream;

public enum AuditLogType {

    ORDER("Order"),ORDERDETAIL("OrderDetail"),PERSON("Person"),PRODUCT("Product");

    AuditLogType(String auditValue){
    }

    public static AuditLogType getAuditLogType(String type){
        return  Stream.of(AuditLogType.values())
                .filter(auditLogType -> auditLogType.name().equalsIgnoreCase(type))
                .findAny()
                .orElseThrow(()-> new CustomException("No Valid Log Type Found"));
    }

}
