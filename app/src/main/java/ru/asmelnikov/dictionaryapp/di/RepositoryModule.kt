package ru.asmelnikov.dictionaryapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import ru.asmelnikov.dictionaryapp.data.DefinitionRepositoryImpl
import ru.asmelnikov.dictionaryapp.data.DictionaryApi
import ru.asmelnikov.dictionaryapp.domain.DefinitionRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDefinitionRepository(
        dictionaryApi: DictionaryApi,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): DefinitionRepository {
        return DefinitionRepositoryImpl(
            dictionaryApi = dictionaryApi,
            ioDispatcher = ioDispatcher
        )
    }
}