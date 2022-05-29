package ru.net.arh.study.service.repositories.calls.wordcollection

import org.springframework.jdbc.`object`.SqlUpdate
import org.springframework.jdbc.core.SqlParameter
import org.springframework.stereotype.Component
import java.sql.Types
import javax.sql.DataSource

@Component
class DeleteWordCollection(dataSource: DataSource): SqlUpdate()  {
    init {
        setDataSource(dataSource)
        sql = """
            delete from word_collection 
            where id = :p_id
        """
        declareParameter(SqlParameter("p_id", Types.BIGINT))
    }
}