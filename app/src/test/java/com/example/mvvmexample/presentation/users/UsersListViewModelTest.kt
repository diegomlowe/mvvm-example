package com.example.mvvmexample.presentation.users

import app.cash.turbine.test
import com.example.mvvmexample.base.Resource
import com.example.mvvmexample.domain.model.Address
import com.example.mvvmexample.domain.model.Company
import com.example.mvvmexample.domain.model.Geo
import com.example.mvvmexample.domain.model.Login
import com.example.mvvmexample.domain.model.User
import com.example.mvvmexample.domain.usecase.GetRemoteUsersListUseCase
import com.example.mvvmexample.presentation.MainDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class UsersListViewModelTest {

    @get: Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: UsersListViewModel

    @MockK
    private lateinit var getRemoteUsersListUseCase: GetRemoteUsersListUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `getUsersList should emit loading state followed by success state`() = runTest {

        val mockUsers = listOf(
            User(
                id = 1,
                firstname = "John",
                lastname = "Doe",
                email = "john.doe@example.com",
                birthDate = "1985-06-15",
                login = Login(
                    uuid = "123e4567-e89b-12d3-a456-426614174000",
                    username = "johndoe",
                    password = "password123",
                    md5 = "098f6bcd4621d373cade4e832627b4f6",
                    sha1 = "40bd001563085fc35165329ea1ff5c5ecbdbbeef",
                    registered = "2022-01-01T12:00:00Z"
                ),
                address = Address(
                    street = "123 Main St",
                    suite = "Apt 1",
                    city = "Springfield",
                    zipcode = "12345",
                    geo = Geo(lat = "37.7749", lng = "-122.4194")
                ),
                phone = "555-1234",
                website = "https://johndoe.com",
                company = Company(
                    name = "Doe Industries",
                    catchPhrase = "Innovation for the future",
                    bs = "empower dynamic solutions"
                )
            ),
            User(
                id = 2,
                firstname = "Jane",
                lastname = "Doe",
                email = "jane.doe@example.com",
                birthDate = "1990-04-10",
                login = Login(
                    uuid = "123e4567-e89b-12d3-a456-426614174001",
                    username = "janedoe",
                    password = "securepass456",
                    md5 = "5f4dcc3b5aa765d61d8327deb882cf99",
                    sha1 = "d033e22ae348aeb5660fc2140aec35850c4da997",
                    registered = "2021-05-15T08:30:00Z"
                ),
                address = Address(
                    street = "456 Elm St",
                    suite = "Suite 200",
                    city = "Shelbyville",
                    zipcode = "67890",
                    geo = Geo(lat = "40.7128", lng = "-74.0060")
                ),
                phone = "555-5678",
                website = "https://janedoe.com",
                company = Company(
                    name = "Jane's Creations",
                    catchPhrase = "Designing your dreams",
                    bs = "revolutionize market strategies"
                )
            )
        )

        every {
            getRemoteUsersListUseCase()
        } returns flow {
            emit(Resource.Loading())
            emit(Resource.Success(mockUsers))
        }

        viewModel = UsersListViewModel(getRemoteUsersListUseCase)

        viewModel.uiState.test {
            val successState = awaitItem()
            assertFalse(successState.isLoading)
            assertEquals(mockUsers, successState.data)
            assertEquals("", successState.error)

            cancelAndIgnoreRemainingEvents()
        }

    }

    @Test
    fun `getUsersList should emit loading state followed by failure state`() = runTest {

        val errorMessage = "An unexpected error occurred"

        every {
            getRemoteUsersListUseCase.invoke()
        } returns flow {
            emit(Resource.Loading())
            emit(Resource.Failure(errorMessage, null)) // Correctly emit failure
        }

        viewModel = UsersListViewModel(getRemoteUsersListUseCase)

        viewModel.uiState.test {
            val failureState = awaitItem()
            assertFalse(failureState.isLoading)
            assertNull(failureState.data)
            assertEquals(errorMessage, failureState.error)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `getUsersList should emit loading state followed by empty success state`() = runTest {

        val emptyUsers = emptyList<User>()

        every {
            getRemoteUsersListUseCase()
        } returns flow {
            emit(Resource.Loading())
            emit(Resource.Success(emptyUsers))
        }

        viewModel = UsersListViewModel(getRemoteUsersListUseCase)

        viewModel.uiState.test {
            val successState = awaitItem()
            assertFalse(successState.isLoading)
            assertTrue(successState.data!!.isEmpty())
            assertEquals("", successState.error)

            cancelAndIgnoreRemainingEvents()
        }
    }
}

