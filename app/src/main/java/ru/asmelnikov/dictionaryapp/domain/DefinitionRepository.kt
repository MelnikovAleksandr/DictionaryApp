package ru.asmelnikov.dictionaryapp.domain

import kotlinx.coroutines.flow.Flow
import ru.asmelnikov.dictionaryapp.common.Resource
import ru.asmelnikov.dictionaryapp.data.dto.DefinitionResponseModelItem

interface DefinitionRepository {

    suspend fun getDefinition(word: String): Flow<Resource<List<DefinitionResponseModelItem>>>
    
}