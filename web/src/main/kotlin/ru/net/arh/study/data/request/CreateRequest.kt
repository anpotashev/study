package ru.net.arh.study.data.request

import com.fasterxml.jackson.annotation.JsonTypeName
import ru.net.arh.study.data.Payload

@JsonTypeName("CREATE")
data class CreateRequest(
    val payload: Payload,
    val wordCollectionId: Long? = null,
    val wordId: Long? = null
) : BaseRequest()