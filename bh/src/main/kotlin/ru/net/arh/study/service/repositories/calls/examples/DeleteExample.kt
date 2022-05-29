package ru.net.arh.study.service.repositories.calls.examples

import org.springframework.jdbc.`object`.SqlUpdate
import org.springframework.jdbc.core.SqlParameter
import org.springframework.stereotype.Component
import java.sql.Types
import javax.sql.DataSource

@Component
class DeleteExample(dataSource: DataSource): SqlUpdate()  {
    init {
        setDataSource(dataSource)
        sql = """
            delete from word_example 
            where id = :p_id
        """
        declareParameter(SqlParameter("p_id", Types.BIGINT))
    }
}