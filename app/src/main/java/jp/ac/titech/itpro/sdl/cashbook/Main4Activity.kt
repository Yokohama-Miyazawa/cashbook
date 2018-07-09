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
import java.util.ArrayList;
import java.util.Locale;


class Main4Activity : AppCompatActivity() {
    private var textView: TextView? = null

    private var lang: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "音声認識"

        // 言語選択 0:日本語、1:英語、2:オフライン、その他:General
        lang = 0

        // 認識結果を表示させる
        textView = findViewById(R.id.text_view) as TextView

        val buttonStart = findViewById(R.id.button_start) as Button
        buttonStart.setOnClickListener {
            // 音声認識を開始
            speech()
        }
    }

    private fun speech() {
        // 音声認識の　Intent インスタンス
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

        if (lang == 0) {
            // 日本語
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.JAPAN.toString())
        } else if (lang == 1) {
            // 英語
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH.toString())
        } else if (lang == 2) {
            // Off line mode
            intent.putExtra(RecognizerIntent.EXTRA_PREFER_OFFLINE, true)
        } else {
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        }

        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 100)
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "音声を入力")

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
                textView!!.text = candidates[0]
            }
        }
    }

    companion object {
        private val REQUEST_CODE = 1000
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }
}