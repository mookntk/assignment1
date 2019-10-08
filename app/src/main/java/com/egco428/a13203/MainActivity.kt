package com.egco428.a13203

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.egco428.a13203.Models.FortuneCookies
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_newfortunecookies.*
import kotlinx.android.synthetic.main.listview_row.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(main_toolbar as Toolbar?)

        val data = Dataprovider.getData()
        var adapter = CourseArrayAdapter(this,data)
        Mainlistview.adapter = adapter

        val newResult = intent.getSerializableExtra("newresult") as? FortuneCookies
        if(newResult?.meaning != null && newResult?.date != null && newResult?.message != null ){
            data.add(newResult)
        }
        else{
            Log.d("newResult","null")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId ==R.id.add){
            var add_intent = Intent(this,Newfortunecookies::class.java)
            startActivity(add_intent)
        }
        return super.onOptionsItemSelected(item)
    }

    private class CourseArrayAdapter(var context: Context,var objects: ArrayList<FortuneCookies>):BaseAdapter(){

        override fun getItem(position: Int): Any {
            return objects[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return objects.size
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            val cookiesItem: View
            if(convertView == null){
                val layoutInflator = LayoutInflater.from(viewGroup!!.context)//!!=not null
                cookiesItem = layoutInflator.inflate(R.layout.listview_row, viewGroup, false)
                val viewHolder = ViewHolder(cookiesItem.txtmsg,cookiesItem.txtdate,cookiesItem.imgresult)
                cookiesItem.tag = viewHolder
            }else{
                cookiesItem = convertView
            }
            cookiesItem.setOnClickListener {
                //delete
                cookiesItem.animate().setDuration(1000).alpha(0f).withEndAction ({
                    objects.removeAt(position)
                    notifyDataSetChanged()
                    cookiesItem.setAlpha(1.0F)
                })
            }
            val viewHolder = cookiesItem.tag as ViewHolder
            viewHolder.message.text = objects.get(position).message
            viewHolder.date.text = objects.get(position).toString()
            viewHolder.image.setImageResource(R.drawable.opened_cookie)
            cookiesItem.txtimage.text = objects.get(position).message
            if(objects[position].meaning == "positive"){
                cookiesItem.txtmsg.setTextColor(Color.parseColor("#03A9F4"))
            }
            else{
                cookiesItem.txtmsg.setTextColor(Color.parseColor("#FF9800"))
            }
            return cookiesItem
        }

    }
    private class ViewHolder(val message: TextView,val date: TextView, val image: ImageView){

    }
}
