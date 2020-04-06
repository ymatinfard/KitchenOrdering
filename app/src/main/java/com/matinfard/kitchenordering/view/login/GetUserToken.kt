package com.matinfard.kitchenordering.view.login

import com.matinfard.kitchenordering.data.Repository
import com.matinfard.kitchenordering.exception.Failure
import com.matinfard.kitchenordering.core.functional.Result
import com.matinfard.kitchenordering.core.interactor.UseCase
import com.matinfard.kitchenordering.data.model.UserAuthData
import com.matinfard.kitchenordering.data.model.UserToken

class GetUserToken(val repository: Repository): UseCase<UserToken, GetUserToken.Params>() {

    override suspend fun run(params: Params): Result<Failure, UserToken> =
        repository.getUserToken(params.userAuthData)

    data class Params(val userAuthData: UserAuthData)
}