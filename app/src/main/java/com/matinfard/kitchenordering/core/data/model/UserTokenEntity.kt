package com.matinfard.kitchenordering.data.model

/**
 * Data model of token of user
 */
class UserTokenEntity(private val token: String?){

    fun toUserToken() = UserToken(token)
}