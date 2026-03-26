package com.pasto.emergency.service;

import com.pasto.emergency.model.dto.AiResultDTO;
import com.pasto.emergency.model.dto.ReportRequestDTO;
import com.pasto.emergency.model.dto.ReportResponseDTO;
import com.pasto.emergency.model.entity.Emergency;
import com.pasto.emergency.model.entity.Report;
import com.pasto.emergency.model.entity.User;
import com.pasto.emergency.model.enums.EmergencyStatus;
import com.pasto.emergency.model.enums.EmergencyType;
import com.pasto.emergency.model.enums.ServiceType;
import com.pasto.emergency.repository.EmergencyRepository;
import com.pasto.emergency.repository.ReportRepository;
import com.pasto.emergency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private EmergencyRepository emergencyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AiClassificationService aiService;

    public ReportResponseDTO processReport(MultipartFile image,
                                           ReportRequestDTO dto,
                                           String userEmail) throws IOException {

        // 1. Buscar usuario o crearlo automáticamente si no existe
        User user = userRepository.findByEmail(userEmail)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setEmail(userEmail);
                    newUser.setPassword("temporal");
                    newUser.setFullName(userEmail.split("@")[0]);
                    newUser.setRole("CITIZEN");
                    return userRepository.save(newUser);
                });

        // 2. Guardar imagen localmente
        String uploadDir = "uploads/";
        new File(uploadDir).mkdirs();
        String filename = UUID.randomUUID() + "_" + image.getOriginalFilename();
        String imageUrl = uploadDir + filename;
        image.transferTo(new File(imageUrl));

        // 3. Clasificar con IA
        AiResultDTO aiResult = aiService.classify(image.getOriginalFilename());
        EmergencyType emergencyType = aiService.mapToEmergencyType(aiResult.getLabel());
        ServiceType service = aiService.determineService(emergencyType);

        // 4. Guardar reporte
        Report report = new Report();
        report.setUser(user);
        report.setImageUrl(imageUrl);
        report.setLatitude(dto.getLatitude());
        report.setLongitude(dto.getLongitude());
        report.setDescription(dto.getDescription());
        report.setAiClassification(emergencyType);
        report.setAiConfidence(aiResult.getConfidence());
        report.setStatus("PROCESSED");
        reportRepository.save(report);

        // 5. Crear emergencia
        Emergency emergency = new Emergency();
        emergency.setReport(report);
        emergency.setType(emergencyType);
        emergency.setAssignedService(service);
        emergency.setStatus(EmergencyStatus.CREATED);
        emergencyRepository.save(emergency);

        // 6. Retornar respuesta
        ReportResponseDTO response = new ReportResponseDTO();
        response.setId(report.getId());
        response.setClassification(emergencyType);
        response.setConfidence(aiResult.getConfidence());
        response.setAssignedService(service);
        response.setStatus("CREATED");
        response.setMessage("Emergencia reportada. " + service.name() + " ha sido notificado.");
        response.setCreatedAt(report.getCreatedAt());
        return response;
    }

    public List<Report> getReportsByUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return reportRepository.findByUserIdOrderByCreatedAtDesc(user.getId());
    }

    public Report findById(Long id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado"));
    }
}