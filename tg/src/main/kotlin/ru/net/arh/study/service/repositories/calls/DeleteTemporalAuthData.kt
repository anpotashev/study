package ru.net.arh.study.service.repositories.calls

import org.springframework.jdbc.`object`.SqlUpdate
import org.springframework.jdbc.core.SqlParameter
import org.springframework.stereotype.Component
import java.sql.Types
import javax.sql.DataSource

@Component
class DeleteTemporalAuthData(dataSource: DataSource): SqlUpdate() {
    init {
        setDataSource(dataSource)
        sql = """
            delete from auth_data 
            where code = :p_code
            """
        declareParameter(SqlParameter("p_code", Types.VARCHAR))
    }
}