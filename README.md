# mvvm-example

Layers:
	. Presentation 
	. Domain
	. Data
	. Base
	. DI

Stack:
	. Views: Jetpack Compose
	. DI: Hilt
	. Networking: Retrofit
	. UnitTest: JUnit4

Details:
	. ViewModels: All viewModels extend from BaseViewModel
	. UseCases: All use cases that fetch data extend from BaseRemoteUseCase
	. All composable screens use UiStateHandler composable to manage ui states (Loading, Success, Error)
	. Included one test class for each class type
	. UsersListViewModel’s empty state test is a fail when all tests are run, but a pass when only the viewModel’s tests are run.
