package com.example.mvvmexample.domain.model

import com.example.mvvmexample.data.network.dto.UserDto

data class User(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val email: String,
    val birthDate: String,
    val login: Login,
    val address: Address,
    val phone: String,
    val website: String,
    val company: Company
)

data class Login(
    val uuid: String,
    val username: String,
    val password: String,
    val md5: String,
    val sha1: String,
    val registered: String
)

data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: Geo
)

data class Geo(
    val lat: String,
    val lng: String
)

data class Company(
    val name: String,
    val catchPhrase: String,
    val bs: String
)

fun UserDto.toUserDomain(): User {
    return User(
        id = id,
        firstname = firstname,
        lastname = lastname,
        email = email,
        birthDate = birthDate,
        login = Login(
            uuid = login.uuid,
            username = login.username,
            password = login.password,
            md5 = login.md5,
            sha1 = login.sha1,
            registered = login.registered
        ),
        address = Address(
            street = address.street,
            suite = address.suite,
            city = address.city,
            zipcode = address.zipcode,
            geo = Geo(
                lat = address.geo.lat,
                lng = address.geo.lng
            )
        ),
        phone = phone,
        website = website,
        company = Company(
            name = company.name,
            catchPhrase = company.catchPhrase,
            bs = company.bs
        )
    )
}