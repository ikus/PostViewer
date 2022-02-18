package com.example.postviewer

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.postviewer.data.PostRepository
import dagger.hilt.android.testing.CustomTestApplication
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */


@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var repository: PostRepository

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun simpleTest() {
        assert( 2 + 2 >3 )
    }

    @Test
    fun getPostFromApi() {
        CoroutineScope(Dispatchers.IO).launch {
            var result = repository.getPostsFromDb()
            assertNotNull(result)
            assert(result.size>0)
        }
    }

    @Test
     fun getCommentFromPostFromApi() {
        CoroutineScope(Dispatchers.IO).launch {
            var result = repository.getPostsFromApi()
            assertNotNull(result)
            assert(result.size>0)
            var comments = result?.get(0).id?.let { repository.getCommentsFromApi(it) }
            assertNotNull(result)
        }
    }
    
}