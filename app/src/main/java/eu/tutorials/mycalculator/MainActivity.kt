package eu.tutorials.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null
    var  lastNumeric:Boolean = false
    var lastDot:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput = findViewById(R.id.tvInput)
    }
    fun onDigit(view: View) {
       tvInput?.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }
    fun onclear(view: View){
        tvInput?.text = ""
    }
    fun onDecimalpoint(view: View){
        if (lastNumeric && !lastDot){
            tvInput?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }
    fun onoperator(view: View){
        tvInput?.text?.let {
            
            if (lastNumeric && !isoperatorAdded(it.toString())){
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }
    fun onEqual(view: View){
        if (lastNumeric){
            var tvValue = tvInput?.text.toString()

            var prefix = ""

            try {
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                    
                }
                if(tvValue.contains("-")){
                    val splitValue = tvValue.split("-")

                    var one = splitValue[0] //99
                    var two = splitValue[1] //1

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removeZerAfterDot((one.toDouble() - two.toDouble()).toString())
                }else if(tvValue.contains("+")){
                    val splitValue = tvValue.split("+")

                    var one = splitValue[0] //99
                    var two = splitValue[1] //1

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removeZerAfterDot((one.toDouble() + two.toDouble()).toString())
                }else if(tvValue.contains("/")){
                    val splitValue = tvValue.split("/")

                    var one = splitValue[0] //99
                    var two = splitValue[1] //1

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removeZerAfterDot((one.toDouble() / two.toDouble()).toString())
                }else if(tvValue.contains("*")){
                    val splitValue = tvValue.split("*")

                    var one = splitValue[0] //99
                    var two = splitValue[1] //1

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removeZerAfterDot((one.toDouble() * two.toDouble()).toString())
                }


        }catch (e: ArithmeticException){
            e.printStackTrace()
           }
        }
    }

    private fun removeZerAfterDot(result: String) : String{
        var value = result
        if (result.contains(".0"))
            value = result.substring(0,result.length - 2 )
        return value
    }

    private fun isoperatorAdded(value: String): Boolean {
         return if(value.startsWith("-")) {
             false
         }else{
             value.contains("/")
             value.contains("*")
             value.contains("+")
             value.contains("-")

         }

    }
    }
