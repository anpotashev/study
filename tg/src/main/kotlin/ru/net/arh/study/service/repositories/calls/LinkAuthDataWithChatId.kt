package ru.net.arh.study.service.repositories.calls

import org.springframework.jdbc.`object`.SqlUpdate
import org.springframework.jdbc.core.SqlParameter
import org.springframework.stereotype.Component
import java.sql.Types
import javax.sql.DataSource

@Component
class LinkAuthDataWithChatId(dataSource: DataSource): SqlUpdate() {
    init {
        setDataSource(dataSource)
        sql = """
            insert into telegram_auth_data(chat_id, auth_code)
            values (:p_chat_id, :p_code)
            on conflict (chat_id) do update set auth_code = :p_code
            """
        declareParameter(SqlParameter("p_chat_id", Types.BIGINT))
        declareParameter(SqlParameter("p_code", Types.VARCHAR))
    }
}