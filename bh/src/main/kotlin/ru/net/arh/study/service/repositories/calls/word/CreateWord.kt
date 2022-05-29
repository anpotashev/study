package ru.net.arh.study.service.repositories.calls.word

import org.springframework.jdbc.`object`.SqlUpdate
import org.springframework.jdbc.core.SqlOutParameter
import org.springframework.jdbc.core.SqlParameter
import org.springframework.stereotype.Component
import java.sql.Types
import javax.sql.DataSource

@Component
class CreateWord(dataSource: DataSource): SqlUpdate() {
    init {
        setDataSource(dataSource)
        sql = """
            insert into word (original, translated, sound_url, word_collection_id)
            values (:p_original, :p_translated, :p_sound_url, :p_word_collection_id)
        """
        setGeneratedKeysColumnNames("id")
        declareParameter(SqlParameter("p_word_collection_id", Types.BIGINT))
        declareParameter(SqlParameter("p_original", Types.VARCHAR))
        declareParameter(SqlParameter("p_translated", Types.VARCHAR))
        declareParameter(SqlParameter("p_sound_url", Types.VARCHAR))
    }
}