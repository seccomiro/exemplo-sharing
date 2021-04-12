package com.ifpr.lubank.databases.DAO

import androidx.room.*
import com.ifpr.lubank.models.User
import com.ifpr.lubank.util.Util

@Dao
interface UserDAO {


    @Query("Select * FROM users")
    fun getAll(): List<User>

    @Query("Select * FROM users WHERE id = (:id)")
    fun getUser(id: Int): List<User>

    @Query("SELECT * from users WHERE username = :username AND password = :password")
    fun login(username: String, password: String): Boolean

    @Query("SELECT * from users WHERE username = :username AND password = :password")
    fun user(username: String, password: String): User


    @Query("SELECT * from users WHERE users.id = :id ")
    fun userById(id: Long?): User

    @Query("SELECT * from users WHERE users.username LIKE :name ")
    fun userByName(name: String): User

    @Insert
    fun insert(user: User): Long

    @Delete
    fun delete(user: User)

    @Update
    fun update(user: User)


}