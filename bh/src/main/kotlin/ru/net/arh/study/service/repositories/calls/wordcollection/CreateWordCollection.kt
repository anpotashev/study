package ru.net.arh.study.service.repositories.calls.wordcollection

import org.springframework.jdbc.`object`.SqlUpdate
import org.springframework.jdbc.core.SqlOutParameter
import org.springframework.jdbc.core.SqlParameter
import org.springframework.stereotype.Component
import java.sql.Types
import javax.sql.DataSource

@Component
class CreateWordCollection(dataSource: DataSource): SqlUpdate() {
    init {
        setDataSource(dataSource)
        sql = """
            insert into word_collection (user_id, name)
            values (:p_user_id, :p_name)
        """
        setGeneratedKeysColumnNames("id")
        declareParameter(SqlParameter("p_user_id", Types.BIGINT))
        declareParameter(SqlParameter("p_name", Types.VARCHAR))
    }
}