package com.yo1000.demo.repository;

import com.yo1000.demo.model.Note;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * リポジトリとはすなわち、永続化を表現するものです。
 * 古くは DAO と表現されることが多かったかもしれませんが、
 * データソースの種類が時代とともに増加したことに連れ、
 * 表現が変わってきているように感じます。
 *
 * そのため、メソッドの命名には様々なデータソースにおいて、
 * 広く伝わる汎用的な名前をつけてください。
 *
 * より具体的には JPA で採用されている
 * 命名規則に従うのがひとつのプラクティスといえるでしょう。
 *
 * Notice:
 *   対応しないものもあるので、まずは JPA ベースで対応する命名を探して、
 *   対応が存在しない事が判明した場合には、規則から外れた命名を認めます。
 *
 * 今回は、`Note` というモデルに対する
 * 読み書きの操作なので、以下のようなメソッドとなるでしょう。
 *
 * - 読み取り操作における JPA 命名: find / findAll / findBy{ConditionColumn}
 * - 書き込み操作における JPA 命名: save
 */
@Repository
@Mapper
public interface NoteRepository {
    List<Note> findAll();
    int save(Note item);
}

