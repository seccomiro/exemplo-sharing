package com.ifpr.lubank.databases

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ifpr.lubank.databases.DAO.RecordDAO
import com.ifpr.lubank.databases.DAO.UserDAO
import com.ifpr.lubank.models.Record
import com.ifpr.lubank.models.User

@Database(entities = [User::class, Record::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usersDao(): UserDAO
    abstract fun recordsDao(): RecordDAO


    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "bank.db"
            ).allowMainThreadQueries().build()
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}