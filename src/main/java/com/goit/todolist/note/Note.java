package com.goit.todolist.note;

import lombok.Data;

import java.util.UUID;

@Data
public class Note {
    private Long id;
    private String title;
    private String content;
}
