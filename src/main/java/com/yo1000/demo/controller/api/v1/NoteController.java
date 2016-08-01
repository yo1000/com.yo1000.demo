package com.yo1000.demo.controller.api.v1;

import com.yo1000.demo.model.Note;
import com.yo1000.demo.repository.NoteRepository;
import com.yo1000.demo.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("note")
public class NoteController {
    private NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    /**
     * ノートの一覧を取得します。
     *
     * @return ノートの一覧。
     */
    @GetMapping("")
    public Object getIndex() {
        return getNoteService().readAll();
    }

    /**
     * 今回はデモアプリのため、確認しやすいように、あえて HTTP GET としています。
     * 本来は HTTP POST のコントローラメソッドとして定義します。
     *
     * @return 書き込み成功したレコードの件数
     */
    @GetMapping("write")
    @ResponseStatus(HttpStatus.CREATED)
    public Object getWrite(@RequestParam String title, @RequestParam(required = false) String note) {
        return getNoteService().write(new Note(title, note, new Date()));
    }

    public NoteService getNoteService() {
        return noteService;
    }
}

