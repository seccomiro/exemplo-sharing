package com.ifpr.lubank.models

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ifpr.lubank.databases.AppDatabase
import com.ifpr.lubank.util.Util

@Entity(tableName = "users")
data class User(
    @ColumnInfo(name = "username")
    var username: String,
    var password: String,
    var balance: Double = 0.0,
    var enabled: Boolean = true
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

    override fun equals(other: Any?) = other is User && this.id == other.id



}

