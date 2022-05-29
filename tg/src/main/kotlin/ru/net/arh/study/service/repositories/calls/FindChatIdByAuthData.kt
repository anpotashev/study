package ru.net.arh.study.service.repositories.calls

import org.springframework.jdbc.`object`.GenericSqlQuery
import org.springframework.jdbc.core.SqlParameter
import org.springframework.stereotype.Component
import java.sql.Types
import javax.sql.DataSource

@Component
class FindChatIdByAuthData(dataSource: DataSource): GenericSqlQuery<Long>() {

    init {
        setDataSource(dataSource)
        setRowMapper({ rs, _ -> rs.getLong("chat_id")})
        sql = """
            select tad.chat_id 
              from telegram_auth_data tad 
              join auth_data ad on ad.code = tad.auth_code
             where ad.code = :p_code
               and ad.single_time_code = :p_single_time_code"""
        declareParameter(SqlParameter("p_code", Types.VARCHAR))
        declareParameter(SqlParameter("p_single_time_code", Types.VARCHAR))
    }
}