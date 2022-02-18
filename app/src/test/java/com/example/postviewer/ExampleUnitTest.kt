package com.example.postviewer

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.postviewer.data.PostRepository
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import javax.inject.Inject

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ExampleUnitTest {



    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}