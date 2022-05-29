package ru.net.arh.study.service.repositories.calls.quest

import org.springframework.jdbc.`object`.SqlUpdate
import org.springframework.jdbc.core.SqlParameter
import org.springframework.stereotype.Component
import java.sql.Types
import javax.sql.DataSource

@Component
class SaveQuestResult(dataSource: DataSource) : SqlUpdate(){

    init {
        setDataSource(dataSource)
        sql = """
            update word
               set success_answers_count = success_answers_count + case when (:p_success) then 1 else 0 end,
                   error_answers_count = error_answers_count + case when (:p_success) then 0 else 1 end
             where id = :p_id;
        """.trimIndent()
        declareParameter(SqlParameter("p_id", Types.BIGINT))
        declareParameter(SqlParameter("p_success", Types.BOOLEAN))
    }
}