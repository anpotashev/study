package ru.net.arh.study.service.repositories.calls.wordcollection

import org.springframework.jdbc.`object`.SqlUpdate
import org.springframework.jdbc.core.SqlParameter
import org.springframework.stereotype.Component
import java.sql.Types
import javax.sql.DataSource

@Component
class UpdateWordCollection(dataSource: DataSource): SqlUpdate()  {
    init {
        setDataSource(dataSource)
        sql = """
            update word_collection
               set name = :p_name
             where id = :p_id
        """
        declareParameter(SqlParameter("p_id", Types.BIGINT))
        declareParameter(SqlParameter("p_name", Types.VARCHAR))
    }
}