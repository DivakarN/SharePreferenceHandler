package com.sysaxiom.sharepreferencehandler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sysaxiom.sharepreferencehandler.Handler.Companion.BOOLEAN
import com.sysaxiom.sharepreferencehandler.Handler.Companion.FLOAT
import com.sysaxiom.sharepreferencehandler.Handler.Companion.INT
import com.sysaxiom.sharepreferencehandler.Handler.Companion.LONG
import com.sysaxiom.sharepreferencehandler.Handler.Companion.STRING
import com.sysaxiom.sharepreferencehandler.Handler.Companion.clearValue
import com.sysaxiom.sharepreferencehandler.Handler.Companion.clearValues
import com.sysaxiom.sharepreferencehandler.Handler.Companion.getPreferenceValue
import com.sysaxiom.sharepreferencehandler.Handler.Companion.savePreferenceValue

class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clearValues(application)

        savePreferenceValue("StringValue","StringValue",application)
        println(tag + getPreferenceValue("StringValue", STRING, application))
        savePreferenceValue("IntValue",1000,application)
        println(tag + getPreferenceValue("IntValue", INT, application))
        savePreferenceValue("LongValue",10000000,application)
        println(tag + getPreferenceValue("LongValue", LONG, application))
        savePreferenceValue("FloatValue",1000.000F,application)
        println(tag + getPreferenceValue("FloatValue", FLOAT, application))
        savePreferenceValue("BooleanValue",true,application)
        println(tag + getPreferenceValue("BooleanValue", BOOLEAN, application))

        clearValue("StringValue",application)
        println(tag + getPreferenceValue("StringValue", STRING, application))
        clearValue("IntValue",application)
        println(tag + getPreferenceValue("IntValue", INT, application))
        clearValue("LongValue",application)
        println(tag + getPreferenceValue("LongValue", LONG, application))
        clearValue("FloatValue",application)
        println(tag + getPreferenceValue("FloatValue", FLOAT, application))
        clearValue("BooleanValue",application)
        println(tag + getPreferenceValue("BooleanValue", BOOLEAN, application))

    }
}