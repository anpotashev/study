package ru.net.arh.study.service.repositories

import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository
import ru.net.arh.study.data.Example
import ru.net.arh.study.service.repositories.calls.examples.*

@Repository
class ExampleRepositoryImpl(
    private val createExample: CreateExample,
    private val getExample: GetExample,
    private val getExamples: GetExamples,
    private val deleteExample: DeleteExample,
    private val updateExample: UpdateExample
) : ExampleRepository {

    override fun create(wordId: Long, text: String): Long {
        val params = hashMapOf(
            "p_word_id" to wordId,
            "p_text" to text
        )
        val keyHolder = GeneratedKeyHolder()
        createExample.updateByNamedParam(params, keyHolder)
        return keyHolder.key as Long
    }

    override fun update(exampleId: Long, text: String) {
        val params = hashMapOf(
            "p_id" to exampleId,
            "p_text" to text
        )
        updateExample.updateByNamedParam(params)
    }

    override fun getAll(wordId: Long): List<Example> {
        val params = hashMapOf(
            "p_word_id" to wordId
        )
        return getExamples.executeByNamedParam(params)
    }

    override fun get(exampleId: Long): Example? {
        val params = hashMapOf(
            "p_id" to exampleId
        )
        return getExample.findObjectByNamedParam(params)
    }

    override fun delete(exampleId: Long) {
        val params = hashMapOf(
            "p_id" to exampleId
        )
        deleteExample.updateByNamedParam(params)
    }
}