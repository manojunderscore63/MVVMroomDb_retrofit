package com.app.mvvmroomdb.data

import androidx.lifecycle.LiveData
import androidx.room.*

//Data Access Object

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id asc")
    fun readAllData(): LiveData<List<User>>

    @Update
    suspend fun updateUser(user: User?)

    @Delete
    suspend fun deleteUser(user: User?)

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()
}