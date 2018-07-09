package jp.ac.titech.itpro.sdl.cashbook

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.content.ContentValues
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Date
import java.text.SimpleDateFormat
import java.util.Locale
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class MainActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TEXTDATA = "cashbook.intent.TEXTDATA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Preferenceを取得
        val pref = getSharedPreferences("Hoge", MODE_PRIVATE)
        /*
        button_gotograph.setOnClickListener {
            val intent = Intent(this, Main2Activity::class.java)
            intent.putExtra(EXTRA_TEXTDATA, "Good Morning!")
            startActivity(intent)
        }
        */
        button_gotolist.setOnClickListener {
            val intent = Intent(this, Main3Activity::class.java)
            startActivity(intent)
        }

        button_gotospeech.setOnClickListener {
            val intent = Intent(this, Main4Activity::class.java)
            startActivity(intent)
        }

        button_pay.setOnClickListener {
            if(edit_text_payamount.text != null && edit_text_payitem.text != null){
                val date = Date()
                val format = SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault())
                val now_time = format.format(date)

                var db = CashDBHelper.getInstance(this);
                db.use{
                    insert(CashDBHelper.tableName,
                            "date" to now_time,
                            "item" to edit_text_payitem.text.toString(),
                            "amount" to "-" + edit_text_payamount.text.toString()
                    )
                }
                // db.close()

                text_view_pay.text = now_time + " " + edit_text_payitem.text.toString() + " " + edit_text_payamount.text.toString()

            }
        }

        button_income.setOnClickListener {
            if(edit_text_incomeamount.text != null && edit_text_incomeitem.text != null){
                val date = Date()
                val format = SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault())
                val now_time = format.format(date)

                var db = CashDBHelper.getInstance(this);
                db.use{
                    insert(CashDBHelper.tableName,
                            "date" to now_time,
                            "item" to edit_text_incomeitem.text.toString(),
                            "amount" to edit_text_incomeamount.text.toString()
                    )
                }

                text_view_income.text = now_time + " " + edit_text_incomeitem.text.toString() + " " + edit_text_incomeamount.text.toString()
            }
        }

        /*
        button_balance.setOnClickListener {
            if(edit_text_balance.text != null){
                text_view_balance.text = edit_text_balance.text.toString()
                pref.edit().putString("key", edit_text_balance.text.toString()).commit()
            }
        }
        */
    }
}
