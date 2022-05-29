package ru.net.arh.study.data.request

import com.fasterxml.jackson.annotation.JsonTypeName
import ru.net.arh.study.data.SubjectType

@JsonTypeName("GET")
data class GetByIdRequest(
    val id: Long,
    val type: SubjectType
) : BaseRequest()