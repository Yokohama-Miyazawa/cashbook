package jp.ac.titech.itpro.sdl.cashbook

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Color
import android.view.MenuItem
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.charts.LineChart
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*
import android.support.v4.content.ContextCompat

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "グラフ画面"

        // Preferenceを取得
        val pref = getSharedPreferences("Hoge", MODE_PRIVATE)
        // Preferenceから読み込み
        preferenceText.text = pref.getString("key", "default")

        // ---------------------------------------------------- //
        var mChart = chart
        mChart!!.description.isEnabled = false

        var data = LineData()

        var set_x = LineDataSet(null, "x")
        set_x.color = Color.BLUE
        set_x.setDrawValues(false)
        set_x.setDrawCircles(false)
        data.addDataSet(set_x)

        var set_y = LineDataSet(null, "y")
        set_y.color = Color.GREEN
        set_y.setDrawValues(false)
        set_y.setDrawCircles(false)
        data.addDataSet(set_y)

        mChart!!.data = data
        // ---------------------------------------------------- //

        // var data: LineData = mChart!!.data
        data = mChart!!.data

        data.addEntry(Entry(0f, 10f), 0)
        data.addEntry(Entry(1f, 20f), 0)
        data.addEntry(Entry(2f, 30f), 0)
        data.addEntry(Entry(3f, 10f), 0)
        data.addEntry(Entry(4f, 40f), 0)
        data.addEntry(Entry(5f, 20f), 0)

        data.addEntry(Entry(0f, 50f), 1)
        data.addEntry(Entry(1f, 40f), 1)
        data.addEntry(Entry(2f, 30f), 1)
        data.addEntry(Entry(3f, 20f), 1)
        data.addEntry(Entry(4f, 10f), 1)
        data.addEntry(Entry(5f, 0f), 1)

        data.notifyDataChanged()

        mChart!!.notifyDataSetChanged()
        mChart!!.invalidate();
        // ---------------------------------------------------- //

        intentTextView.text = intent.getStringExtra(MainActivity.EXTRA_TEXTDATA)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }
}
