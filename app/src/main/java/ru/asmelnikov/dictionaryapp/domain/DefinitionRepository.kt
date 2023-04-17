package ru.asmelnikov.dictionaryapp.domain

import kotlinx.coroutines.flow.Flow
import ru.asmelnikov.dictionaryapp.common.Resource
import ru.asmelnikov.dictionaryapp.data.dto.DefinitionResponseModel

interface DefinitionRepository {

    suspend fun getDefinition(word: String): Flow<Resource<List<DefinitionResponseModel>>>
    
}