package com.example.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleGetResponse {
    private final Long id;
    private final String name;
    private final String contents;
    private final String title;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;

    public ScheduleGetResponse(Long id, String name, String contents, String title, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.name = name;
        this.contents = contents;
        this.title = title;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
