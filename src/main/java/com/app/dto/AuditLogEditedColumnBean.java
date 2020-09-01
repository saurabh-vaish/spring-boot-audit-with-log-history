package com.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
@Setter
public class AuditLogEditedColumnBean {

	private String newValue;

	private String viewOrder;

	private String fieldName;

	private Boolean hasChanged;

	private String displayName;

	private String oldValue;
}
