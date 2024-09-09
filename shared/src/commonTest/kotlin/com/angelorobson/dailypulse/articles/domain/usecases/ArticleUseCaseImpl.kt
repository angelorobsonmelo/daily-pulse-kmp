package com.angelorobson.dailypulse.articles.domain.usecases

import com.angelorobson.dailypulse.articles.domain.models.Article
import com.angelorobson.dailypulse.articles.domain.repositories.ArticlesRepository
import kotlinx.coroutines.test.runTest
import org.kodein.mock.Fake
import org.kodein.mock.Mock
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class ArticleUseCaseImpl : TestsWithMocks() {

    private lateinit var articleUseCase: ArticleUseCase

    @Mock
    lateinit var repository: ArticlesRepository

    @Fake
    lateinit var article: Article

    override fun setUpMocks() = injectMocks(mocker)

    @BeforeTest
    fun setUp() {
        articleUseCase = ArticleUseCase(repository)
    }

    @Test
    fun `should invoke without force fetch and return list of articles from local database`() =
        runTest {
            everySuspending { repository.getLocalArticles() } returns listOf(article)

            articleUseCase(false)
                .collect {
                    assertTrue(it.isNotEmpty())
                }
        }

    @Test
    fun `should invoke with force fetch and return list of articles from remote database`() =
        runTest {
            everySuspending { repository.fetchRemoteArticles() } returns listOf(article)
            everySuspending { repository.clearLocalArticles() } returns Unit
            everySuspending { repository.createArticles(listOf(article)) } returns Unit

            articleUseCase(true)
                .collect {
                    assertTrue(it.isNotEmpty())
                }

            verifyWithSuspend {
                repository.clearLocalArticles()
                repository.fetchRemoteArticles()
                repository.createArticles(isAny())
            }
        }

    @Test
    fun `should invoke without force fetch and return list of articles from remote database and create articles`() =
        runTest {
            val list = listOf(article)

            everySuspending { repository.getLocalArticles() } returns listOf()
            everySuspending { repository.fetchRemoteArticles() } returns list
            everySuspending { repository.createArticles(isEqual(list)) } returns Unit

            articleUseCase(false)
                .collect {
                    assertTrue(it.isNotEmpty())
                }

            verifyWithSuspend {
                repository.getLocalArticles()
                repository.fetchRemoteArticles()
                repository.createArticles(isEqual(list))
            }
        }


}