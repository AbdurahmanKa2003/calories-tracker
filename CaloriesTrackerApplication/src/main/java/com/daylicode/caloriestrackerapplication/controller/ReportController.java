package com.daylicode.caloriestrackerapplication.controller;




import com.daylicode.caloriestrackerapplication.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/user/{userId}/date/{date}/calories")
    public ResponseEntity<Integer> getTotalCaloriesForDay(@PathVariable Long userId, @PathVariable String date) {
        return ResponseEntity.ok(reportService.getTotalCaloriesForDay(userId, LocalDate.parse(date)));
    }

    @GetMapping("/user/{userId}/date/{date}/within-limit")
    public ResponseEntity<Boolean> isUserWithinCalorieLimit(@PathVariable Long userId, @PathVariable String date) {
        return ResponseEntity.ok(reportService.isUserWithinCalorieLimit(userId, LocalDate.parse(date)));
    }

    @GetMapping("/user/{userId}/history")
    public ResponseEntity<Map<LocalDate, Integer>> getMealHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(reportService.getMealHistory(userId));
    }
}
