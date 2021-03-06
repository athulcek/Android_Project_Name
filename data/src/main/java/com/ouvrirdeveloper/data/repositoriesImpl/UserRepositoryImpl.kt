package com.ouvrirdeveloper.data.repositoriesImpl

import com.ouvrirdeveloper.data.datasource.UserDataSource
import com.ouvrirdeveloper.data.helper.KeystoreHelper
import com.ouvrirdeveloper.data.helper.PreferenceHelper
import com.ouvrirdeveloper.data.helper.SharedConstants.PREF_USER_PIN
import com.ouvrirdeveloper.domain.models.User
import com.ouvrirdeveloper.domain.models.common.Resource
import com.ouvrirdeveloper.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl(
    private val userDataSource: UserDataSource,
    private val preferenceHelper: PreferenceHelper

) : UserRepository {

    private val keyStoreHelper: KeystoreHelper by lazy {
        KeystoreHelper()
    }

    /* override fun login(loginDetails: User): Flow<Resource<User?>> {
         return flow<Resource<User?>> {
             val kk = safeApiCall {
                 userDataSource.login(loginDetails).data?.toDomainModel()?.also {
                     it.password = loginDetails.password
                     it.userId = loginDetails.userId
                     it.companyId = loginDetails.companyId
                 }
             }
             emit(kk)
         }.flowOn(Dispatchers.IO)
     }




     override fun logOut() {
         *//*clearTokenDetails()
        setIsUserLoggedIn(false)
        val loginDetails = getKeepMeSignedInDetail()
        loginDetails.password = ""
        saveIsKeepMeLoggedIn(loginDetails, isKeepMeLoggedIn())*//*
    }

    override fun isKeepMeLoggedIn(): Boolean {
        return true
    }

    override suspend fun updateDbwithPendingTasks(data: List<PendingTask>) {
        userDataSource.updateDbwithPendingTasks(data.map {
            PendingTaskEntity(
                docsrchcode = it.docsrchcode,
                doctype = it.doctype,
                totalcount = it.totalcount
            )
        })
    }

    override suspend fun getUserFromDisk(): User? {
        if (preferenceHelper.getPref(
                SharedConstants.PrefUtil_IS_LOGGED_IN,
                false
            ) == true && preferenceHelper.getPref(
                SharedConstants.PrefUtil_KEEP_ME_LOGGED_IN,
                false
            ) == true
        ) {
            val u= User(
                userId = preferenceHelper.getPref(SharedConstants.PrefUtil_UNAME, "") ?: "",
                password = preferenceHelper.getPref(SharedConstants.PrefUtil_PASS_KEY, "") ?: "",
                companyId = preferenceHelper.getPref(SharedConstants.PrefUtil_COMPANY_ID, 0) ?: 0
            )
            return u
        }
        return null
    }

    override fun saveToDisk(userId: String, password: String, companyId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            preferenceHelper.setPref(SharedConstants.PrefUtil_KEEP_ME_LOGGED_IN, true)
            preferenceHelper.setPref(SharedConstants.PrefUtil_IS_LOGGED_IN, true)
            preferenceHelper.setPref(SharedConstants.PrefUtil_UNAME, userId)
            preferenceHelper.setPref(SharedConstants.PrefUtil_PASS_KEY, password)
            preferenceHelper.setPref(SharedConstants.PrefUtil_COMPANY_ID, companyId)
        }
    }


    private fun getEncryptedData(dataToEncrypt: String?) = dataToEncrypt?.let {
        keyStoreHelper.encryptData(it)
    }*/

    override fun getLoggedUser(): User? {
         val user = preferenceHelper.getPref(PREF_USER_PIN, -1)
         return if (user != null && user > -1) User(user) else null
    }

    override fun authenticate(pin: Int): Flow<Resource<Boolean>> {
        return flow {
            kotlinx.coroutines.delay(2000)
            emit(Resource.Success(true))
        }
    }


}