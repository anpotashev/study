package ru.net.arh.study.service.repositories.calls.examples

import org.springframework.jdbc.`object`.GenericSqlQuery
import org.springframework.jdbc.core.SqlParameter
import org.springframework.stereotype.Component
import ru.net.arh.study.data.Example
import ru.net.arh.study.data.WordCollection
import ru.net.arh.study.service.repositories.rowmappers.ExampleRowMapper
import ru.net.arh.study.service.repositories.rowmappers.WordCollectionRowMapper
import java.sql.Types
import javax.sql.DataSource

@Component
class GetExamples(
    dataSource: DataSource,
    exampleRowMapper: ExampleRowMapper
): GenericSqlQuery<Example>() {

    init {
        setDataSource(dataSource)
        sql = """
            select id, text
              from word_example
             where word_id =:p_word_id
        """
        setRowMapper(exampleRowMapper)
        declareParameter(SqlParameter("p_word_id", Types.BIGINT))
    }
}