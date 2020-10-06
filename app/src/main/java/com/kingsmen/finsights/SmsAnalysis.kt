package com.kingsmen.finsights


class SmsAnalysis() {
    var expenseList = ArrayList<ExpenseData>()
    suspend fun getData(smsList: ArrayList<SmsData>): ArrayList<ExpenseData> {
        smsList.forEach(){
            //Log.d("/n-----------------------------------/n",it.toString())
            val isFood = it.senderName.contains("SODEXO")
            if(isFood){
                val splitMessage = it.message.split(" ")
                val indexOfStore = splitMessage.lastIndexOf("at") + 1
                    expenseList.add(ExpenseData(splitMessage[indexOfStore],splitMessage[0],"Food", it.date))
            }
        }
        return expenseList
    }
}

