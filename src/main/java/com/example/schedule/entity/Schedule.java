package com.example.schedule.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table (name = "schedules")
@NoArgsConstructor (access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String title;
    String contents;
    String name;

    public Schedule(String title, String contents, String name) {
        this.title = title;
        this.contents = contents;
        this.name = name;
    }

    public void update(String contents, String name, String title) {
        this.title = title;
        this.contents = contents;
        this.name = name;
    }
}
