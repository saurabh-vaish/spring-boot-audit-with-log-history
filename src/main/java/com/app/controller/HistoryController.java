package com.app.controller;

import com.app.dto.ApiResponse;
import com.app.dto.AuditLogDto;
import com.app.models.Person;
import com.app.service.AuditLogService;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api
@Log4j2
@RestController
@RequestMapping(value = "/person")
public class HistoryController {

    @Autowired
    private AuditLogService auditLogService;

    @PostMapping("/get-revision")
    public ApiResponse getRevision(@RequestBody AuditLogDto auditLogDto){
        return auditLogService.getRevision(auditLogDto);
    }

    @PostMapping("/get-last-revision}")
    public ApiResponse getLastRevision(@RequestBody AuditLogDto auditLogDto){
        return auditLogService.getLastRevision(auditLogDto);
    }

    @PostMapping("/get-all-revisions")
    public ApiResponse getAllRevisions(@RequestBody AuditLogDto auditLogDto){
        return auditLogService.getAllRevisions(auditLogDto);
    }

    @PostMapping("/get-all-revisions-by-page/")
    public ApiResponse getAllRevisionsByPage(@RequestBody AuditLogDto auditLogDto){
        return auditLogService.getAllRevisionsByPage(auditLogDto);
    }

    @PostMapping("/get-all-history-by-page/")
    public ApiResponse getAllHistoryByPage(@RequestBody AuditLogDto auditLogDto){
        return auditLogService.getAllHistoryByPage(auditLogDto);
    }

}
