package com.goit.todolist.note;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Random;


class NoteServiceImplTest {

    private NoteServiceImpl noteService = new NoteServiceImpl();
    private Note originalNote = new Note();

    @BeforeEach
    void beforeEach() {
        //Given
        originalNote.setId(Math.abs(new Random().nextLong()));
        originalNote.setTitle("Test title");
        originalNote.setContent("Test content");
    }

    @Test
    void listAllTest() {
        //When
        Note expectedNote = noteService.add(originalNote);
        List<Note> expected = Collections.singletonList(expectedNote);
        List<Note> actual = noteService.listAll();

        //Then
        Assertions.assertEquals(expected.size(), actual.size());
    }

    @Test
    void addTest() {
        //When
        Note expectedNote = noteService.add(originalNote);

        //Then
        Assertions.assertEquals(expectedNote.getTitle(), originalNote.getTitle());
        Assertions.assertEquals(expectedNote.getContent(), originalNote.getContent());
    }

    @Test
    void deleteByIdTest() {
        //When
        Note expectedNote = noteService.add(originalNote);
        long savedId = expectedNote.getId();
        noteService.deleteById(savedId);

        //Then
        Assertions.assertThrows(IllegalArgumentException.class, () -> noteService.getById(savedId));
    }

    @Test
    void updateTest() {
        //When
        Note savedNote = noteService.add(originalNote);
        long savedId = savedNote.getId();

        Note updatedNote = new Note();
        updatedNote.setId(savedId);
        updatedNote.setTitle("New Title");
        updatedNote.setContent("New Content");

        noteService.update(updatedNote);

        Note actualNote = noteService.getById(updatedNote.getId());

        //Then
        Assertions.assertEquals(savedNote.getId(), actualNote.getId());
        Assertions.assertEquals("New Title", actualNote.getTitle());
        Assertions.assertEquals("New Content", actualNote.getContent());
    }

    @Test
    void getById() {
        //When
        Note expectedNote = noteService.add(originalNote);
        long expectedId = originalNote.getId();

        //Then
        Note actualNote = noteService.getById(expectedId);
        Long actualId = actualNote.getId();
        Assertions.assertEquals(actualId, expectedId);
    }
}