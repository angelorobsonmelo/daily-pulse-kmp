package com.angelorobson.dailypulse.articles.data

import com.angelorobson.dailypulse.articles.data.local.mappers.ArticleEntityToDomainMapper
import com.angelorobson.dailypulse.articles.data.network.mappers.ArticleResponseToDomainMapper
import com.angelorobson.dailypulse.articles.data.network.responses.ArticleRawResponse
import com.angelorobson.dailypulse.articles.data.repositories.ArticlesRepositoryImpl
import com.angelorobson.dailypulse.articles.domain.local.ArticlesLocalDataSource
import com.angelorobson.dailypulse.articles.domain.models.Article
import com.angelorobson.dailypulse.articles.domain.network.ArticleRemoteDataSource
import com.angelorobson.dailypulse.articles.domain.repositories.ArticlesRepository
import com.angelorobson.dailypulse.db.ArticleEntity
import kotlinx.coroutines.runBlocking
import org.kodein.mock.Fake
import org.kodein.mock.Mock
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class ArticlesRepositoryImplTest : TestsWithMocks() {

    override fun setUpMocks() = injectMocks(mocker)

    @Mock
    lateinit var remoteDataSource: ArticleRemoteDataSource

    @Mock
    lateinit var localDataSource: ArticlesLocalDataSource

    @Fake
    lateinit var entity: ArticleEntity


    private val articles = listOf(
        Article(
            title = "title",
            desc = "desc",
            date = "2024-09-06T00:00:00Z",
            imageUrl = "imageUrl"
        )
    )

    private val errorMsg = "Exception occurred"


    private val response = ArticleRawResponse(
        title = "title",
        desc = "desc",
        date = "2024-09-06T00:00:00Z",
        imageUrl = "imageUrl"
    )

    private val repository: ArticlesRepository by withMocks {
        val articlesMapper = ArticleEntityToDomainMapper()
        val articleResponseToDomainMapper = ArticleResponseToDomainMapper()

        ArticlesRepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource,
            articlesMapper = articlesMapper,
            articleResponseToDomainMapper = articleResponseToDomainMapper
        )
    }

    @Test
    fun `getLocalArticles should return entities list`() = runBlocking {
        every { localDataSource.getAllArticles() } returns listOf(entity)

        val list = repository.getLocalArticles()

        verify { localDataSource.getAllArticles() }

        assertFalse(list.isEmpty())
        assertTrue(list.isNotEmpty())
    }

    @Test
    fun `getLocalArticles should return error`() = runBlocking {
        every { localDataSource.getAllArticles() } runs { error(errorMsg) }

        val ex = assertFailsWith<IllegalStateException> {
            repository.getLocalArticles()
        }

        assertNotNull(ex)
        assertEquals(errorMsg, ex.message)
    }

    @Test
    fun `fetchRemoteArticles should return a list`() = runBlocking {
        everySuspending { remoteDataSource.fetchArticles() } returns listOf(response)

        val list = repository.fetchRemoteArticles()

        verifyWithSuspend { remoteDataSource.fetchArticles() }

        assertFalse(list.isEmpty())
        assertTrue(list.isNotEmpty())
    }

    @Test
    fun `fetchRemoteArticles should return error`() = runBlocking {
        everySuspending { remoteDataSource.fetchArticles() } runs { error(errorMsg) }

        val ex = assertFailsWith<IllegalStateException> {
            repository.fetchRemoteArticles()
        }

        assertNotNull(ex)
        assertEquals(errorMsg, ex.message)
    }

    @Test
    fun `createArticles should perform action`() = runBlocking {
        every { localDataSource.insertArticles(articles) } returns Unit

        repository.createArticles(articles)

        verify { localDataSource.insertArticles(isEqual(articles)) }
    }

    @Test
    fun `createArticles should return error`() = runBlocking {
        every { localDataSource.insertArticles(isAny()) } runs { error(errorMsg) }

        val ex = assertFailsWith<IllegalStateException> {
            repository.createArticles(listOf())
        }

        assertNotNull(ex)
        assertEquals(errorMsg, ex.message)
    }

    @Test
    fun `clearLocalArticles should perform action`() = runBlocking {
        every { localDataSource.clearArticles() } returns Unit

        repository.clearLocalArticles()

        verify { localDataSource.clearArticles() }
    }

    @Test
    fun `clearArticles should return error`() = runBlocking {
        every { localDataSource.clearArticles() } runs { error(errorMsg) }

        val ex = assertFailsWith<IllegalStateException> {
            repository.clearLocalArticles()
        }

        assertNotNull(ex)
        assertEquals(errorMsg, ex.message)
    }

}