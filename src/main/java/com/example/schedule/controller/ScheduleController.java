package com.example.schedule.controller;

import com.example.schedule.dto.*;
import com.example.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<ScheduleCreatResponse> create(@RequestBody ScheduleCreatRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(request));
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleGetResponse>>getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAll());
    }

    @GetMapping("/schedules/{schedulesId}")
    public ResponseEntity<ScheduleGetResponse> getOne(@PathVariable Long schedulesId){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findOne(schedulesId));

    }

    @PutMapping("/schedules/{schedulesId}")
    public ResponseEntity<ScheduleUpdateResponse> update(
            @PathVariable Long schedulesId,
            @RequestBody ScheduleUpdateRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.update(schedulesId, request));
    }

    @DeleteMapping("/schedules/{schedulesId}")
    public void delete(@PathVariable Long schedulesId){
        scheduleService.delete(schedulesId);
    }
}
