package com.app.dto;

import io.swagger.models.auth.In;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
@Setter
public class AuditLogDto {

	private String auditLogType;

	private Integer auditLogId;

	private Integer pageNo;

	private Integer version;

}
