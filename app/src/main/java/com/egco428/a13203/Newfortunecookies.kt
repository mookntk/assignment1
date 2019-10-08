package com.egco428.a13203

import android.content.Intent
import android.graphics.Color
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.egco428.a13203.Common.HTTPDataHandler
import com.egco428.a13203.Models.FortuneCookies
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_newfortunecookies.*
import java.sql.Timestamp
import java.util.*

class Newfortunecookies : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        var click = 0
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newfortunecookies)
        setSupportActionBar(toolbar as Toolbar?)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val stamp = Timestamp(System.currentTimeMillis())
        val date = Date(stamp.getTime())

        var new = FortuneCookies("default",date,1,"default")
        btn.setOnClickListener {
            if(click == 0){
                var asyncTask = object: AsyncTask<String, String, String>(){

                    override fun doInBackground(vararg params: String?): String {
                        val http = HTTPDataHandler()
                        return http.GetHTTPDataHandler("http://www.atilal.com/cookies.php")
                    }

                    override fun onPreExecute() {//เริ่มต้นส่งrequest
                        Toast.makeText(this@Newfortunecookies,"Waiting", Toast.LENGTH_SHORT).show()
                    }

                    override fun onPostExecute(result: String?) {
                        click = 1
                        val newcookie = Gson().fromJson(result,FortuneCookies::class.java)
                        btn.text = "Save"
                        if(newcookie.meaning == "positive"){
                            txtresult.setTextColor(Color.parseColor("#03A9F4"))
                        }
                        else{
                            txtresult.setTextColor(Color.parseColor("#FF9800"))
                        }
                        new = FortuneCookies(newcookie.message,date,R.drawable.opened_cookie,newcookie.meaning)
                        txtresult.text = "Result : "+newcookie.message
                        txtdate.text = new.toString()
                        imageView.setImageResource(R.drawable.opened_cookie)
                        resultImg.text = newcookie.message
                        resultImg.visibility = VISIBLE
                    }

                }
                asyncTask.execute()
            }
            else{
                var Main = Intent(this,MainActivity::class.java)
                Main.putExtra("newresult", new)
                startActivity(Main)
            }
        }
    }
}
