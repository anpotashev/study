package ru.net.arh.study.service.repositories.rowmappers

import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import ru.net.arh.study.data.WordCollection
import java.sql.ResultSet

@Component
class WordCollectionRowMapper : RowMapper<WordCollection>{
    override fun mapRow(rs: ResultSet, rowNum: Int): WordCollection {
        return WordCollection(
            id = rs.getLong("id"),
            name = rs.getString("name")
        )
    }

}