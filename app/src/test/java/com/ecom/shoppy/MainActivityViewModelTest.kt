package com.ecom.shoppy

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.ecom.shoppy.model.Product
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MainActivityViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    lateinit var mainActivityViewModel: MainActivityViewModel
    @MockK
    lateinit var product: Product

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        mainActivityViewModel = MainActivityViewModel()
    }

    @After
    fun tearDown() {

    }


    @Test
    fun `verify addProductToCart returns false when quantity is zero`() = runBlockingTest{
        //Given
        coEvery { product.name } returns "test"
        //When
        val result = mainActivityViewModel.addProductToCart(
            quantity = 0,
            productId = 1,
            variantIndex = 0,
            product = product
        )
        //Then
        assertThat(mainActivityViewModel.itemAddedToCartLiveData.getOrAwaitValue() ).isFalse()
        assertThat(result).isFalse()
    }

    @Test
    fun `verify addProductToCart returns true when quantity is non-zero`() {
        //Given
        coEvery { product.id } returns 1
        coEvery { product.categoryId } returns 2
        //When
        val result = mainActivityViewModel.addProductToCart(
            quantity = 1,
            productId = 1,
            variantIndex = 0,
            product = product
        )
        //Then
        assertThat(result).isTrue()
    }

}
