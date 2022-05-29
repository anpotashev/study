package ru.net.arh.study.service.repositories.calls.word

import org.springframework.jdbc.`object`.SqlUpdate
import org.springframework.jdbc.core.SqlParameter
import org.springframework.stereotype.Component
import java.sql.Types
import javax.sql.DataSource

@Component
class UpdateWord(dataSource: DataSource): SqlUpdate()  {
    init {
        setDataSource(dataSource)
        sql = """
            update word
             set original = :p_original,
                 translated = :p_translated,
                 sound_url = :p_sound_url
            where id = :p_id
        """
        declareParameter(SqlParameter("p_id", Types.BIGINT))
        declareParameter(SqlParameter("p_original", Types.VARCHAR))
        declareParameter(SqlParameter("p_translated", Types.VARCHAR))
        declareParameter(SqlParameter("p_sound_url", Types.VARCHAR))
    }
}