package ru.net.arh.study.service.repositories.calls.wordcollection

import org.springframework.jdbc.`object`.GenericSqlQuery
import org.springframework.jdbc.core.SqlParameter
import org.springframework.stereotype.Component
import ru.net.arh.study.data.WordCollection
import ru.net.arh.study.service.repositories.rowmappers.WordCollectionRowMapper
import java.sql.Types
import javax.sql.DataSource

@Component
class GetWordCollection(
    dataSource: DataSource,
    wordCollectionRowMapper: WordCollectionRowMapper
): GenericSqlQuery<WordCollection>() {

    init {
        setDataSource(dataSource)
        sql = """
            select id, name
              from word_collection
             where id = :p_id
        """
        setRowMapper(wordCollectionRowMapper)
        declareParameter(SqlParameter("p_id", Types.BIGINT))
    }
}