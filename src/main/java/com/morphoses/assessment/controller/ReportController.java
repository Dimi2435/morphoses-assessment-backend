package com.morphoses.assessment.controller;

import com.morphoses.assessment.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/session-summary/{sessionId}")
    public ResponseEntity<Map<String, Object>> getSessionSummary(@PathVariable UUID sessionId) {
        Map<String, Object> summary = reportService.getSessionSummary(sessionId);
        return ResponseEntity.ok(summary);
    }
}