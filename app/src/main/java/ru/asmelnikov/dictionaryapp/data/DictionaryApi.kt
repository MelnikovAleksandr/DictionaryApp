package ru.asmelnikov.dictionaryapp.data

import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path
import ru.asmelnikov.dictionaryapp.data.dto.DefinitionResponseModelItem
import ru.asmelnikov.dictionaryapp.data.dto.ErrorResponse

interface DictionaryApi {

    @GET("api/v2/entries/en/{word}")
    suspend fun getDefinition(@Path("word") word: String)
            : NetworkResponse<List<DefinitionResponseModelItem>, ErrorResponse>
}