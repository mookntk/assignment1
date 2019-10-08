package com.egco428.a13203

import com.egco428.a13203.Models.FortuneCookies
import java.util.*
import kotlin.collections.ArrayList

object Dataprovider {
    private val data = ArrayList<FortuneCookies>()

    fun getData():ArrayList<FortuneCookies>{
        return data
    }

    init { //constructor
        data.add(
            FortuneCookies(
                "You're Lucky",
                Date(116, 9, 10, 12, 0),
                2,
                "positive"
            )
        )
        data.add(
            FortuneCookies(
                "You will get A",
                Date(117, 9, 10, 13, 0),
                2,
                "positive"
            )
        )
        data.add(
            FortuneCookies(
                "Don't Panic",
                Date(116, 9, 10, 13, 0),
                2,
                "negative"
            )
        )
    }

}