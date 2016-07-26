package com.yo1000.demo.service;

import com.yo1000.demo.model.Note;
import com.yo1000.demo.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * サービスとはすなわち、業務処理、プロセスを表現するものです。
 * そのため、メソッドの命名にはその業務処理に即した名前をつけてください。
 *
 * 今回は、`Note` というメモを取ったり（書いたり）、読んだりすることを
 * 主眼においた業務処理なので、以下のようなメソッドとなるでしょう。
 *
 * - `Note`(Service) を 読む `read`
 * - `Note`(Service) を 書く `write`
 */
@Service
public class NoteService {
    private NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> readAll() {
        return getNoteRepository().findAll();
    }

    @Transactional
    public int write(Note item) {
        return getNoteRepository().save(item);
    }

    public NoteRepository getNoteRepository() {
        return noteRepository;
    }
}
