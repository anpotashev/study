package ru.net.arh.study.service.repositories.calls

import org.springframework.jdbc.`object`.SqlUpdate
import org.springframework.jdbc.core.SqlParameter
import org.springframework.stereotype.Component
import java.sql.Types
import javax.sql.DataSource

@Component
class SaveCodeAndSingleTimeCode(dataSource: DataSource): SqlUpdate() {
    init {
        setDataSource(dataSource)
        sql = """
            insert into auth_data (code, single_time_code)
            values (:p_code, :p_single_time_code)
            """
        declareParameter(SqlParameter("p_code", Types.VARCHAR))
        declareParameter(SqlParameter("p_single_time_code", Types.VARCHAR))
    }
}