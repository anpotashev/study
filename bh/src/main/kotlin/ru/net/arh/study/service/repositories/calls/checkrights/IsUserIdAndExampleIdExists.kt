package ru.net.arh.study.service.repositories.calls.checkrights

import org.springframework.jdbc.`object`.GenericSqlQuery
import org.springframework.jdbc.core.SqlParameter
import org.springframework.stereotype.Component
import java.sql.Types
import javax.sql.DataSource

@Component
class IsUserIdAndExampleIdExists(
    dataSource: DataSource
): GenericSqlQuery<Boolean>() {

    init {
        setDataSource(dataSource)
        setRowMapper({ rs, _ -> rs.getBoolean("p_exists") })
        sql = """
            select exists (select from word_collection wc 
              join word w on w.word_collection_id = wc.id 
              join word_example we on we.word_id = w.id
             where we.id = :p_id 
               and wc.user_id = :p_user_id) as p_exists
        """.trimIndent()
        declareParameter(SqlParameter("p_id", Types.BIGINT))
        declareParameter(SqlParameter("p_user_id", Types.BIGINT))
    }
}