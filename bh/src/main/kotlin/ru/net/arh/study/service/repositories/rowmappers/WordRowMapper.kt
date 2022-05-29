package ru.net.arh.study.service.repositories.rowmappers

import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import ru.net.arh.study.data.Word
import ru.net.arh.study.data.WordCollection
import java.sql.ResultSet

@Component
class WordRowMapper : RowMapper<Word>{
    override fun mapRow(rs: ResultSet, rowNum: Int): Word {
        return Word(
            original = rs.getString("original"),
            translated = rs.getString("translated"),
            soundUrl = rs.getString("sound_url"),
            id = rs.getLong("id"),
            successAnswer = rs.getInt("success_answers_count"),
            errorAnswer = rs.getInt("error_answers_count")
        )
    }

}