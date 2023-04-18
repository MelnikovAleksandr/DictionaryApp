package ru.asmelnikov.dictionaryapp.data

import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.asmelnikov.dictionaryapp.common.Resource
import ru.asmelnikov.dictionaryapp.data.dto.DefinitionResponseModel
import ru.asmelnikov.dictionaryapp.data.dto.DefinitionResponseModelItem
import ru.asmelnikov.dictionaryapp.di.IoDispatcher
import ru.asmelnikov.dictionaryapp.domain.DefinitionRepository
import javax.inject.Inject

class DefinitionRepositoryImpl @Inject constructor(
    private val dictionaryApi: DictionaryApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : DefinitionRepository {

    override suspend fun getDefinition(word: String): Flow<Resource<List<DefinitionResponseModelItem>>> =
        flow {
            emit(Resource.Loading())

            when (val response = dictionaryApi.getDefinition(word)) {
                is NetworkResponse.Success -> {
                    val definitionResponse = response.body
                    emit(Resource.Success(definitionResponse))
                }
                is NetworkResponse.ServerError -> {
                    emit(Resource.Error(response.body?.message ?: "Try again a new word!"))
                }
                is NetworkResponse.NetworkError -> {
                    emit(
                        Resource.Error("Please check connection")
                    )
                }
                is NetworkResponse.UnknownError -> {
                    emit(
                        Resource.Error("Unknown error")
                    )
                }
            }
        }.flowOn(ioDispatcher)
}