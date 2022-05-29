package ru.net.arh.study.service.repositories.calls

import org.springframework.jdbc.`object`.GenericSqlQuery
import org.springframework.jdbc.core.SqlParameter
import org.springframework.stereotype.Component
import java.sql.Types
import javax.sql.DataSource

@Component
class GetChatIdByCode(dataSource: DataSource): GenericSqlQuery<Long>() {

    init {
        setDataSource(dataSource)
        setRowMapper({ rs, _ -> rs.getLong("chat_id")})
        sql = """
            select chat_id 
              from telegram_auth_data
             where auth_code = :p_code
             """
        declareParameter(SqlParameter("p_code", Types.VARCHAR))
    }
}