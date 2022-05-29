package ru.net.arh.study.data

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
@JsonSubTypes(
    JsonSubTypes.Type(WordCollection::class),
    JsonSubTypes.Type(Word::class),
    JsonSubTypes.Type(Example::class),
)
abstract class Payload