package ru.net.arh.study.data.request

import com.fasterxml.jackson.annotation.JsonTypeName
import ru.net.arh.study.data.Payload

@JsonTypeName("UPDATE")
data class UpdateRequest(
    val payload: Payload
) : BaseRequest()