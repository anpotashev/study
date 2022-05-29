package ru.net.arh.study.service.repositories.calls.quest

import org.springframework.jdbc.core.SqlOutParameter
import org.springframework.jdbc.core.SqlParameter
import org.springframework.jdbc.core.simple.SimpleJdbcCall
import org.springframework.stereotype.Component
import java.sql.Types
import javax.sql.DataSource

@Component
class QuestWord(dataSource: DataSource): SimpleJdbcCall(dataSource) {

    init {
        withFunctionName("quest_word")
            .withoutProcedureColumnMetaDataAccess()
            .declareParameters(
                SqlOutParameter("p_err_code", Types.VARCHAR),
                SqlOutParameter("p_word_id", Types.BIGINT),
                SqlParameter("p_collection_id", Types.BIGINT)
            )
    }
}