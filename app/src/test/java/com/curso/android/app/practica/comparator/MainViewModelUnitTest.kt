package com.curso.android.app.practica.comparator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.curso.android.app.practica.comparator.view.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class MainViewModelUnitTest {

    private lateinit var viewModel: MainViewModel

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup(){
        Dispatchers.setMain(dispatcher)
        viewModel = MainViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun mainViewModel_CheckInitialValue() = runTest {
        val firstString = "Pulse 'comparar' para verificar\\n si las cadenas son iguales"
        val value = viewModel.comparator.value?.equality
        assertEquals(value, "Pulse 'comparar' para verificar\\n si las cadenas son iguales")
    }

    @Test
    fun mainViewModel_CheckEquality() = runTest {
        launch {
            viewModel.compareStrings("texto", "texto")

        }
        advanceUntilIdle()

        val value = viewModel.comparator.value?.equality
        assertEquals(value, "Las cadenas son iguales")
    }

    @Test
    fun mainViewModel_CheckNotEquality() = runTest {
        launch {
            viewModel.compareStrings("texto", "textoDiferente")

        }
        advanceUntilIdle()

        val value = viewModel.comparator.value?.equality
        assertEquals(value, "Las cadenas son diferentes")
    }
}
