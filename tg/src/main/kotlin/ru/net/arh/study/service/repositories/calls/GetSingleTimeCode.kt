package ru.net.arh.study.service.repositories.calls

import org.springframework.jdbc.`object`.GenericSqlQuery
import org.springframework.jdbc.core.SqlParameter
import org.springframework.stereotype.Component
import java.sql.Types
import javax.sql.DataSource

@Component
class GetSingleTimeCode(dataSource: DataSource): GenericSqlQuery<String>() {

    init {
        setDataSource(dataSource)
        setRowMapper({ rs, _ -> rs.getString("single_time_code")})
        sql = """
            select single_time_code 
              from auth_data
             where code = :p_code
             """
        declareParameter(SqlParameter("p_code", Types.VARCHAR))
    }
}