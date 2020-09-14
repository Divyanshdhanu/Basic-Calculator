package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var lastNumeric: Boolean= false
    var lastDot: Boolean =false
    var a:Int=0
    var b:Int=0
    fun onDigit(view: View){
        tvInput.append((view as Button).text)
        lastNumeric=true
    }
    fun onClear(view: View){
        tvInput.text=""
        lastNumeric= false
        lastDot=false

    }
    fun onClickDot(view: View){
        if(lastNumeric && !lastDot){
            tvInput.append(".")
            lastDot=true
            lastNumeric=false
        }

    }

    fun onOperator(view: View){
        //a=(tvInput.text.toString().toInt())
        if(lastNumeric && !isOperatorAdded(tvInput.text.toString())) {
            tvInput.append((view as Button).text)
            lastNumeric = false
            lastDot=false
        }
    }

    private fun isOperatorAdded(value: String): Boolean{
        return if (value.startsWith("-")){
            false
        }else{
            value.contains("/") || value.contains("*")
                    || value.contains("+") || value.contains("-")
        }
    }

    fun onEquals(view: View){
        var prefixVal=""
        var tvValue=tvInput.text.toString()
        try {
            if(tvValue.startsWith("-")){
                tvValue=tvValue.substring(1)
                prefixVal="-"
            }

            if(tvValue.contains("-")){
                var arr=tvValue.split("-")
                var one=arr[0].toDouble()
                var two=arr[1].toDouble()
                if(prefixVal=="-"){
                    one=-1*one
                }
                tvInput.text= (one-two).toString()
            }
            else if(tvValue.contains("+")){
                var arr=tvValue.split("+")
                var one=arr[0].toDouble()
                var two=arr[1].toDouble()
                if(prefixVal=="+"){
                    one=-1*one
                }
                tvInput.text= (one+two).toString()
            }
            else if(tvValue.contains("*")){
                var arr=tvValue.split("*")
                var one=arr[0].toDouble()
                var two=arr[1].toDouble()
                if(prefixVal=="*"){
                    one=-1*one
                }
                tvInput.text= (one*two).toString()
            }
            else if(tvValue.contains("/")){
                var arr=tvValue.split("/")
                var one=arr[0].toDouble()
                var two=arr[1].toDouble()
                if(prefixVal=="/"){
                    one=-1*one
                }
                tvInput.text= (one/two).toString()
            }

        }
        catch (e: ArithmeticException){
            e.printStackTrace()
        }
    }
}
