package com.goit.todolist.note;

import com.goit.todolist.storage.Repository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * DAO service implements CRUD-operations for note entity (Note).
 */
@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private Logger log = LoggerFactory.getLogger(NoteServiceImpl.class);

    @Override
    public List<Note> listAll() {
        log.info("Getting all notes...");
        return Repository.NOTES;
    }

    @Override
    public Note add(Note note) {
        note.setId(Math.abs(new Random().nextLong()));
        Repository.NOTES.add(note);
        log.info("Note added!");
        return note;
    }

    @Override
    public void deleteById(long id) {
        Optional<Note> noteToRemove =
                Repository.NOTES.stream()
                        .filter(toRemove -> toRemove.getId().equals(id))
                        .findFirst();
        if (noteToRemove.isPresent()) {
            Repository.NOTES.remove(noteToRemove.get());
            log.info("Note deleted!");
        } else {
            throw new IllegalArgumentException(
                    "Note with id = " + id + " doesn't exist! Please enter other id"
            );
        }
    }

    @Override
    public void update(Note note) {
        for (Note noteToUpdate : Repository.NOTES) {
            if (Objects.equals(noteToUpdate.getId(), note.getId())) {
                noteToUpdate.setTitle(note.getTitle());
                noteToUpdate.setContent(note.getContent());
                log.info("Note updated!");
                return;
            }
        }
        throw new IllegalArgumentException(
                "Note with id = " + note.getId() + " doesn't exist! Please enter other id"
        );
    }

    @Override
    public Note getById(long id) {
        Optional<Note> note = Repository.NOTES.stream()
                .filter(getNote -> getNote.getId().equals(id))
                .findFirst();
        if (!note.isPresent()) {
            throw new IllegalArgumentException(
                    "Note with id = " + id + " doesn't exist! Please enter other id"
            );
        }
        log.info("Note got!");
        return note.get();
    }
}
