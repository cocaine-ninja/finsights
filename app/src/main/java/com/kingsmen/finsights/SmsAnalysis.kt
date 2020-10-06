package com.kingsmen.finsights

import android.util.Log
import com.kingsmen.finsights.dao.Transaction
import java.util.*
import kotlin.collections.ArrayList


class SmsAnalysis() {
    var expenseList = ArrayList<Transaction>()
    fun getData(smsList: ArrayList<SmsData>): ArrayList<Transaction> {
        smsList.forEach(){
            Log.d("/n-----------------------------------/n",it.senderName)
            val isFood = it.senderName.contains("SODEXO")
            if(isFood){
                val splitMessage = it.message.split(" ")
                val indexOfStore = splitMessage.lastIndexOf("at") + 1
                    expenseList.add(Transaction("Food", (splitMessage[0].split("s.")).get(1).toDouble(), it.date, splitMessage[indexOfStore]))
            }
        }
        return expenseList
    }
}

