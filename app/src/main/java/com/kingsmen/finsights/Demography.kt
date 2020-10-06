package com.kingsmen.finsights

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText

class Demography : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demography)

        val smsAck = findViewById<CheckBox>(R.id.smsAck);
        val age = findViewById<EditText>(R.id.age);
        val gender = findViewById<EditText>(R.id.gender);
        val continue_ahead = findViewById<Button>(R.id.continue_ahead);

        val textUsed = arrayOf(
            smsAck
        )
        val hintUsed = arrayOf(
            age,
            gender
        )
        changeLanguageCheckBox(textUsed)
        changeLanguageView(hintUsed)
        continue_ahead.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent);
        }
    }

    fun changeLanguageView(textList: Array<EditText>) {

//        textList.forEach{
//            if(it.hint!=null){
//                Amplify.Predictions.translateText(
//                    it.hint.toString(),
//                    LanguageType.ENGLISH,
//                    LanguageType.HINDI,
//                    { result -> it.hint = result.translatedText },
//                    { error -> Log.e("MyAmplifyApp", "Translation failed", error) }
//                )
//            }
//        }
    }
    fun changeLanguageCheckBox(textList: Array<CheckBox>) {

//        textList.forEach{
//            if(it.text!=null){
//                Amplify.Predictions.translateText(
//                    it.text.toString(),
//                    LanguageType.ENGLISH,
//                    LanguageType.HINDI,
//                    { result -> it.text = result.translatedText },
//                    { error -> Log.e("MyAmplifyApp", "Translation failed", error) }
//                )
//            }
//        }
    }
}