package com.egco428.a13203.Models

import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

class FortuneCookies (val message: String? = null,val date: Date? = null, val imgfile: Int? = null,val meaning: String? = null): Serializable{
    override fun toString(): String {
        val pattern = " dd-MMM-YYYY HH:mm a"
        val simpleDateFormat = SimpleDateFormat(pattern)
        val datestr = simpleDateFormat.format(date)
        return "Date: "+datestr
    }
}