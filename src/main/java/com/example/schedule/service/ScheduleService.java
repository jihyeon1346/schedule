package com.example.schedule.service;

import com.example.schedule.dto.*;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleCreatResponse save(ScheduleCreatRequest request) {
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContents(),
                request.getName());
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleCreatResponse(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getContents(),
                savedSchedule.getName(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt());
    }

    @Transactional(readOnly = true)
    public List<ScheduleGetResponse> findAll() {
        List<Schedule> schedules = (List<Schedule>) scheduleRepository.findAll();
        List<ScheduleGetResponse> dtos = new ArrayList<>();
        for(Schedule calendar : schedules) {
            ScheduleGetResponse response = new ScheduleGetResponse(
                    calendar.getId(),
                    calendar.getName(),
                    calendar.getContents(),
                    calendar.getTitle(),
                    calendar.getCreatedAt(),
                    calendar.getModifiedAt()
            );
            dtos.add(response);
        }
        return dtos;
    }
    @Transactional(readOnly = true)
    public ScheduleGetResponse findOne(Long calendarId) {
        Schedule calendar = scheduleRepository.findById(calendarId).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다.")
        );

        return new ScheduleGetResponse(
                calendar.getId(),
                calendar.getName(),
                calendar.getContents(),
                calendar.getTitle(),
                calendar.getCreatedAt(),
                calendar.getModifiedAt()
        );
    }

    @Transactional
    public ScheduleUpdateResponse update(Long schedulesId, ScheduleUpdateRequest request) {
        Schedule calendar = scheduleRepository.findById(schedulesId).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다."));
        calendar.update(
                request.getContents(),
                request.getName(),
                request.getTitle());

        return new ScheduleUpdateResponse(
                calendar.getId(),
                calendar.getName(),
                calendar.getContents(),
                calendar.getTitle(),
                calendar.getCreatedAt(),
                calendar.getModifiedAt()
        );

    }

    @Transactional
    public void delete(Long calendarsId) {
        boolean existence = scheduleRepository.existsById(calendarsId);
        if (!existence) {
            throw new IllegalStateException("없는 일정입니다.");
        }
        scheduleRepository.deleteById(calendarsId);
    }
}
