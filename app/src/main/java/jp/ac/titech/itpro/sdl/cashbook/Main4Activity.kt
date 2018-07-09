package jp.ac.titech.itpro.sdl.cashbook

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.view.MenuItem
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main4.*
import org.jetbrains.anko.db.insert
import java.text.SimpleDateFormat
import java.util.*


class Main4Activity : AppCompatActivity() {
    private var textView: TextView? = null

    private var item: String = null.toString()
    private var amount: String = null.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "音声認識"

        // 認識結果を表示させる
        textView = findViewById(R.id.text_view) as TextView

        val buttonStart = findViewById(R.id.button_start) as Button
        buttonStart.setOnClickListener {
            // 音声認識を開始
            speech("品目")
            Thread.sleep(6000)
            speech("金額")
        }

        button_set_pay.setOnClickListener {
            val date = Date()
            val format = SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault())
            val now_time = format.format(date)

            var db = CashDBHelper.getInstance(this);
            db.use{
                insert(CashDBHelper.tableName,
                        "date" to now_time,
                        "item" to item,
                        "amount" to "-" + amount
                )
            }
        }

        button_set_income.setOnClickListener {
            val date = Date()
            val format = SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault())
            val now_time = format.format(date)

            var db = CashDBHelper.getInstance(this);
            db.use{
                insert(CashDBHelper.tableName,
                        "date" to now_time,
                        "item" to item,
                        "amount" to amount
                )
            }
        }
    }

    private fun speech(message : String) {
        // 音声認識の　Intent インスタンス
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.JAPAN.toString())

        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 100)
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, message)

        try {
            // インテント発行
            startActivityForResult(intent, REQUEST_CODE)
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
            textView!!.setText(R.string.error)
        }

    }

    // 結果を受け取るために onActivityResult を設置
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // 認識結果を ArrayList で取得
            val candidates = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)

            if (candidates.size > 0) {
                // 認識結果候補で一番有力なものを表示
                val result = candidates[0]
                if(result.indexOf("円") == -1) {  // 品目
                    speech_item.text = result
                    item = result
                } else {  // 金額
                    val result_amount = result.substring(0, result.indexOf("円"))
                    speech_amount.text = result
                    amount = result_amount
                }
                textView!!.text = candidates[0]
            }
        }
    }

    companion object {
        private val REQUEST_CODE = 1000
    }

    // ホーム画面に戻る
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }
}