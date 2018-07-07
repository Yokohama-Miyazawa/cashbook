package jp.ac.titech.itpro.sdl.cashbook

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_pay.setOnClickListener {
            if(edit_text_payamount.text != null && edit_text_payitem.text != null){
                text_view_pay.text = edit_text_payitem.text.toString() + " " + edit_text_payamount.text.toString()
            }
        }

        button_income.setOnClickListener {
            if(edit_text_incomeamount.text != null && edit_text_incomeitem.text != null){
                text_view_income.text = edit_text_incomeitem.text.toString() + " " + edit_text_incomeamount.text.toString()
            }
        }

        button_balance.setOnClickListener {
            if(edit_text_balance.text != null){
                text_view_balance.text = edit_text_balance.text.toString()
            }
        }
    }
}
