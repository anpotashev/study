package ru.net.arh.study.service

import net.devh.boot.grpc.client.inject.GrpcClient
import org.springframework.stereotype.Service
import ru.net.arh.study.bh.dto.BhDto
import ru.net.arh.study.bh.service.WordCollectionServiceGrpc
import ru.net.arh.study.data.WordCollection
import ru.net.arh.study.exception.NoDataFoundException

@Service
class WordCollectoinServiceImpl : WordCollectoinService {

    @GrpcClient("study-bh")
    private lateinit var wordCollectionServiceBlockingStub: WordCollectionServiceGrpc.WordCollectionServiceBlockingStub


    override fun getAll(userId: Long): List<WordCollection> {
        return wordCollectionServiceBlockingStub.getAll(
            BhDto.GetAllCollectionRq.newBuilder()
                .setUserId(userId)
                .build()
        )
            .asSequence().toList()
            .map {
                WordCollection(
                    it.id, it.name
                )
            }
    }

    override fun get(userId: Long, id: Long): WordCollection {
        val current = wordCollectionServiceBlockingStub.getById(
            BhDto.GetCollectionRq.newBuilder()
                .setCollectionId(id)
                .setUserId(userId)
                .build()
        )
        if (current.dataCase == BhDto.NullableWordCollection.DataCase.NULL) {
            throw NoDataFoundException(
                "ROW_NOT_FOUND", "Запись не найдена"
            )
        }
        return current.wordCollection.let {
            WordCollection(it.id, it.name)
        }
    }

    override fun create(userId: Long, collection: WordCollection): WordCollection {
        val createRs = wordCollectionServiceBlockingStub.create(
            BhDto.CreateWordCollectionRq.newBuilder()
                .setUserId(userId)
                .setName(collection.name)
                .build()
        )
        return get(userId, createRs.id)
    }

    override fun update(userId: Long, collection: WordCollection): WordCollection {
        wordCollectionServiceBlockingStub.edit(
            BhDto.RenameWordCollectionRq.newBuilder()
                .setUserId(userId)
                .setWordCollectionId(collection.id)
                .setNewName(collection.name)
                .build()
        )
        return get(userId, collection.id)
    }

    override fun delete(userId: Long, id: Long) {
        wordCollectionServiceBlockingStub.delete(
            BhDto.DeleteWordCollectionRq.newBuilder()
                .setUserId(userId)
                .setWordCollectionId(id)
                .build()
        )
    }

}