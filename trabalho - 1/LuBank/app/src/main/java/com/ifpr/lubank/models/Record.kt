package com.ifpr.lubank.models


import androidx.room.*
import java.text.SimpleDateFormat
import java.util.*


@Entity(tableName = "records")
data class Record(
    var person: String,
    var remarks: String = "",
    var value: Double,
    @ForeignKey(
        entity = User::class,
        parentColumns = ["id"],
        childColumns = ["fk_user"]
    )
    @ColumnInfo(name = "fk_user")
    var fkUser: Long,
    var kind: Boolean,
    var registredAt: String = SimpleDateFormat("dd/MM/yyyy HH:mm").format(Date())

) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

    override fun equals(other: Any?) = other is Record && this.id == other.id
}






