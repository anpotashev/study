package ru.net.arh.study.service.repositories.calls.examples

import org.springframework.jdbc.`object`.SqlUpdate
import org.springframework.jdbc.core.SqlParameter
import org.springframework.stereotype.Component
import java.sql.Types
import javax.sql.DataSource

@Component
class CreateExample(dataSource: DataSource) : SqlUpdate() {
    init {
        setDataSource(dataSource)
        sql = """
            insert into word_example (word_id, text)
            values (:p_word_id, :p_text)
        """
        setGeneratedKeysColumnNames("id")
        declareParameter(SqlParameter("p_word_id", Types.BIGINT))
        declareParameter(SqlParameter("p_text", Types.VARCHAR))
    }
}