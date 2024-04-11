package com.goit.todolist.storage;

import com.goit.todolist.note.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository for temporary notes storage instead of database.
 */
public class Repository {
    public static final List<Note> NOTES = new ArrayList<>();

}
