package ru.net.arh.study.service.repositories.rowmappers

import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import ru.net.arh.study.data.Example
import java.sql.ResultSet

@Component
class ExampleRowMapper: RowMapper<Example> {

    override fun mapRow(rs: ResultSet, rowNum: Int): Example {
        return Example(
            id = rs.getLong("id"),
            text = rs.getString("text")
        )
    }
}