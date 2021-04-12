package com.ifpr.lubank.databases.DAO

import androidx.room.*
import com.ifpr.lubank.models.Record
import com.ifpr.lubank.util.Util

@Dao
interface RecordDAO {

    @Query("Select * FROM records")
    fun getAll(): List<Record>

    @Query("Select * FROM records WHERE id = (:id)")
    fun getRecord(id: Int): List<Record>

    @Insert
    fun insert(record: Record): Long

    @Delete
    fun delete(record: Record)

    @Update
    fun update(record: Record)

    @Query("SELECT * FROM records WHERE (records.fk_user = :id)")
    fun getRecordsByUser(id: Long?): List<Record>

    @Query("SELECT SUM (value) FROM records where (records.fk_user = :id)")
    fun getSumBalance(id: Long): Long

    @Query("SELECT SUM (value) FROM records where (records.fk_user = :id) AND  (records.kind = 0)")
    fun getSumExpenses(id: Long): Long

    @Query("SELECT SUM (value) FROM records where (records.fk_user = :id) AND (records.kind = 1)")
    fun getSumRevenue(id: Long): Long


    @Query("SELECT *FROM records WHERE ( records.fk_user = :id ) and   (records.person LIKE :latter or   records.person LIKE :initialLatter ) ")
    fun getRecordsByPerson(latter: String, initialLatter: String, id: Long?): List<Record>

}
