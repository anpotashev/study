package ru.net.arh.study.service.repositories.calls.word

import org.springframework.jdbc.`object`.GenericSqlQuery
import org.springframework.jdbc.core.SqlParameter
import org.springframework.stereotype.Component
import ru.net.arh.study.data.Word
import ru.net.arh.study.data.WordCollection
import ru.net.arh.study.service.repositories.rowmappers.WordCollectionRowMapper
import ru.net.arh.study.service.repositories.rowmappers.WordRowMapper
import java.sql.Types
import javax.sql.DataSource

@Component
class GetWords(
    dataSource: DataSource,
    wordRowMapper: WordRowMapper
): GenericSqlQuery<Word>() {

    init {
        setDataSource(dataSource)
        sql = """
            select id, original, translated, sound_url, success_answers_count, error_answers_count
              from word
             where word_collection_id = :p_word_collection
        """
        setRowMapper(wordRowMapper)
        declareParameter(SqlParameter("p_word_collection", Types.BIGINT))
    }
}