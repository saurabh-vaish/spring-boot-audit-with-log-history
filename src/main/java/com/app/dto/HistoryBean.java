package com.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@NoArgsConstructor
@Getter
@Setter
public class HistoryBean {

	private String modifiedTime;

	private String modifiedByUserName;

	private Integer version;

	public List<AuditLogEditedColumnBean> auditHistoryEditedColumnBeanList = new ArrayList<AuditLogEditedColumnBean>();
}