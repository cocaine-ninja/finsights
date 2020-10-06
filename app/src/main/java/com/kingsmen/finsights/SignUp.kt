package com.kingsmen.finsights

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.kingsmen.finsights.Demography

class SignUp : AppCompatActivity() {
    var language = "HINDI"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val onboard = findViewById<TextView>(R.id.onboard);
        val phoneNo = findViewById<EditText>(R.id.phoneNo);
        val password = findViewById<EditText>(R.id.password);
        val email = findViewById<EditText>(R.id.email);
        val signup = findViewById<Button>(R.id.signup);

        val textUsed = arrayOf(
            onboard
        )
        val hintUsed = arrayOf(
            phoneNo,
            password,
            email
        )
        changeLanguageEditText(hintUsed)
        changeLanguageTextView(textUsed)
        signup.setOnClickListener{
            val intent = Intent(this, Demography::class.java)
            startActivity(intent);
        }
    }
    fun changeLanguageEditText(textList: Array<EditText>) {

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

    fun changeLanguageTextView(textList: Array<TextView>) {

//        textList.forEach{
//            if(it.text!=null){
//                Amplify.Predictions.translateText(
//                    it.text.toString(),
//                    LanguageType.ENGLISH,
//                    LanguageType.JAPANESE,
//                    { result -> it.text = result.translatedText },
//                    { error -> Log.e("MyAmplifyApp", "Translation failed", error) }
//                )
//            }
//        }

    }
}