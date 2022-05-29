package ru.net.arh.study.data.request

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@action")
@JsonSubTypes(
    JsonSubTypes.Type(CreateRequest::class),
    JsonSubTypes.Type(GetAllRequest::class),
    JsonSubTypes.Type(GetByIdRequest::class),
    JsonSubTypes.Type(UpdateRequest::class),
    JsonSubTypes.Type(DeleteByIdRequest::class),
)
abstract class BaseRequest