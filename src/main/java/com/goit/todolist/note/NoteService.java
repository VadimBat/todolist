package com.goit.todolist.note;

import java.util.List;

/**
 * Interface describing notes objects CRUD operations.
 */
public interface NoteService {
    List<Note> listAll();

    Note add(Note note);

    void deleteById(long id);

    void update(Note note);

    Note getById(long id);
}
