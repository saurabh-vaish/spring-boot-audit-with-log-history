package com.app.service;

import com.app.dto.ApiResponse;
import com.app.dto.AuditLogDto;

public interface AuditLogService {

    public ApiResponse getRevision(AuditLogDto auditLogDto);
    public ApiResponse getLastRevision( AuditLogDto auditLogDto);
    public ApiResponse getAllRevisions(AuditLogDto auditLogDto);
    public ApiResponse getAllRevisionsByPage( AuditLogDto auditLogDto);
    public ApiResponse getAllHistoryByPage( AuditLogDto auditLogDto);

}
