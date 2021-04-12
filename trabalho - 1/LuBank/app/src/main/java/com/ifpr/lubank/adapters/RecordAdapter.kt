package com.ifpr.lubank.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ifpr.lubank.R
import com.ifpr.lubank.databases.AppDatabase
import com.ifpr.lubank.models.Record
import com.ifpr.lubank.util.Util
import kotlinx.android.synthetic.main.item_record.view.*
import kotlinx.android.synthetic.main.item_record.view.txtDate
import kotlinx.android.synthetic.main.item_record.view.txtPerson
import kotlinx.android.synthetic.main.item_record.view.txtValueUser
import kotlinx.android.synthetic.main.item_record_edit.view.txtNewRemarks
import kotlinx.android.synthetic.main.item_record_edit.view.*

class RecordAdapter(context: Context) : RecyclerView.Adapter<RecordAdapter.ViewHolder>() {

    var records = AppDatabase.getInstance(context).recordsDao().getRecordsByUser(Util.user.id)


    var recordsEdit: Boolean = false
    var positionEdit: Int? = null

    override fun getItemViewType(position: Int): Int {
        val record = records[position]


        return if (recordsEdit && position == positionEdit) {
            R.layout.item_record_edit

        } else {
            R.layout.item_record
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(viewType, parent, false)
        )


    override fun getItemCount() = records.size //records.size-1

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val rcd= records[position]

        if (records.isNotEmpty()) {
            val record = records[position]
            holder.fillView(record)
        }


    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n", "ResourceAsColor")
        fun fillView(record: Record) = if (recordsEdit && records.indexOf(record) == positionEdit) {

            itemView.txtNewRemarks.setText(record.remarks.toUpperCase())
            itemView.txtValueUser.text = ": " + record.value.toString()
            itemView.txtDate.text = record.registredAt
            itemView.txtPerson.text = record.person
            itemView.elevation = 5F

            if (record.kind) {
                itemView.card_edit.setCardBackgroundColor(Color.parseColor("#c5e1a5"))
            } else {
                itemView.txtValueUser.text = ": - " + record.value.toString()
                itemView.card_edit.setCardBackgroundColor(Color.parseColor("#f44336"))
            }

            itemView.imgOk.setOnClickListener {
                record.remarks = itemView.txtNewRemarks.text.toString().toUpperCase()

                AppDatabase.getInstance(itemView.context).recordsDao().update(record)
                recordsEdit = false
                notifyItemChanged(positionEdit!!)


            }

            itemView.setOnClickListener {
                recordsEdit = !recordsEdit
                val position = records.indexOf(record)
                positionEdit = position
                notifyItemChanged(position)
            }

        } else {

            itemView.txtTitle.text = record.remarks.toUpperCase()
            itemView.txtValueUser.text = ": " + record.value.toString()
            itemView.txtDate.text = record.registredAt
            itemView.txtPerson.text = record.person
            itemView.elevation = 5F


            if (record.kind) {
                itemView.card.setCardBackgroundColor(Color.parseColor("#c5e1a5"))
            } else {
                itemView.txtValueUser.text = ": - " + record.value.toString()
                itemView.card.setCardBackgroundColor(Color.parseColor("#f44336"))

            }

            itemView.setOnClickListener {
                recordsEdit = !recordsEdit
                val position = records.indexOf(record)
                positionEdit = position
                notifyItemChanged(position)
            }

        }

    }

    fun attData(list: List<Record>, context: Context, flag: Boolean = false) {
        if (flag) {
            this.records = list
            notifyDataSetChanged()

        } else {
            AppDatabase.getInstance(context).recordsDao().getRecordsByUser(Util.user.id)
                .also { this.records = it }
            notifyDataSetChanged()
        }
    }
}