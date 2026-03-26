package com.pasto.emergency.controller;

import com.pasto.emergency.model.dto.ReportRequestDTO;
import com.pasto.emergency.model.dto.ReportResponseDTO;
import com.pasto.emergency.model.entity.Report;
import com.pasto.emergency.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reports")
@CrossOrigin(origins = "*")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ReportResponseDTO> createReport(
            @RequestPart("image") MultipartFile image,
            @RequestPart("data") ReportRequestDTO dto,
            Principal principal) throws IOException {

        ReportResponseDTO response = reportService.processReport(
                image, dto, principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/my")
    public ResponseEntity<List<Report>> myReports(Principal principal) {
        return ResponseEntity.ok(
                reportService.getReportsByUser(principal.getName()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Report> getReport(@PathVariable Long id) {
        return ResponseEntity.ok(reportService.findById(id));
    }
}