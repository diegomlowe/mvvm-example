package com.example.mvvmexample.data.network.dto


data class UserDto(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val email: String,
    val birthDate: String,
    val login: LoginDto,
    val address: AddressDto,
    val phone: String,
    val website: String,
    val company: CompanyDto
)

data class LoginDto(
    val uuid: String,
    val username: String,
    val password: String,
    val md5: String,
    val sha1: String,
    val registered: String
)

data class AddressDto(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: GeoDto
)

data class GeoDto(val lat: String, val lng: String)
data class CompanyDto(val name: String, val catchPhrase: String, val bs: String)
