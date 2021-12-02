package com.ouvrirdeveloper.data.datasource

import com.ouvrirdeveloper.data.api.UserApiService
import com.ouvrirdeveloper.data.database.dao.PendingTaskDao

class UserDataSource(
    private val apiService: UserApiService,
    private val pendingTaskDao: PendingTaskDao
) {


}