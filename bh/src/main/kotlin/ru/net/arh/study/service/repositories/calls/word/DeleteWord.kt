package ru.net.arh.study.service.repositories.calls.word

import org.springframework.jdbc.`object`.SqlUpdate
import org.springframework.jdbc.core.SqlParameter
import org.springframework.stereotype.Component
import java.sql.Types
import javax.sql.DataSource

@Component
class DeleteWord(dataSource: DataSource): SqlUpdate()  {
    init {
        setDataSource(dataSource)
        sql = """
            delete from word 
            where id = :p_id
        """
        declareParameter(SqlParameter("p_id", Types.BIGINT))
    }
}