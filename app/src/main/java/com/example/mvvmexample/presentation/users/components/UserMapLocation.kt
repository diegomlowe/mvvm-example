package com.example.mvvmexample.presentation.users.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mvvmexample.domain.model.User
import com.example.mvvmexample.presentation.UiStateHandler
import com.example.mvvmexample.presentation.users.UserLocationViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState


@Composable
fun UserMapLocationScreen(
    userLocationViewModel: UserLocationViewModel = hiltViewModel(),
    userId: Int
) {
    userLocationViewModel.getUserLocation(userId = userId)

    val uiState = userLocationViewModel.uiState.collectAsState().value

    UiStateHandler(
        isLoading = uiState.isLoading,
        error = uiState.error
    ) {
        uiState.data?.let {
            UserLocationGoogleMaps(it)
        }

    }
}

@Composable
fun UserLocationGoogleMaps(user: User) {
    val marker = LatLng(user.address.geo.lat.toDouble(), user.address.geo.lng.toDouble())
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(marker, 10f) // Adjust zoom level as needed
    }
    GoogleMap(
        modifier = Modifier
            .fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = rememberMarkerState(position = marker),
            title = "${user.firstname} ${user.lastname}"
        )
    }
}