package ru.net.arh.study.service.repositories.calls.examples

import org.springframework.jdbc.`object`.SqlUpdate
import org.springframework.jdbc.core.SqlParameter
import org.springframework.stereotype.Component
import java.sql.Types
import javax.sql.DataSource

@Component
class UpdateExample(dataSource: DataSource): SqlUpdate()  {
    init {
        setDataSource(dataSource)
        sql = """
            update word_example
               set text = :p_text
             where id = :p_id
        """
        declareParameter(SqlParameter("p_id", Types.BIGINT))
        declareParameter(SqlParameter("p_text", Types.VARCHAR))
    }
}