package ru.net.arh.study.data.request

import com.fasterxml.jackson.annotation.JsonTypeName
import ru.net.arh.study.data.SubjectType

@JsonTypeName("GET_ALL")
data class GetAllRequest(
    val type: SubjectType,
    val collectionId: Long? = null,
    val wordId: Long? = null
) : BaseRequest()