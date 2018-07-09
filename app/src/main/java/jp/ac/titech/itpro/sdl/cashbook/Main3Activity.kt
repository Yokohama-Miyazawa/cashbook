package jp.ac.titech.itpro.sdl.cashbook

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.db.select
import kotlinx.android.synthetic.main.activity_main3.list

class Main3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "一覧画面"

        val db = CashDBHelper.getInstance(this)
        val dataList =  db.readableDatabase.select(CashDBHelper.tableName).parseList<ListData>(ListDataParser())

        //text_view_income.text = dataList.toString()
        list.adapter = CashListAdapter(baseContext, R.layout.row).apply {
            addAll(dataList)
        }
    }
}
