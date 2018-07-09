package jp.ac.titech.itpro.sdl.cashbook

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper

class CashDBHelper(var context: Context):ManagedSQLiteOpenHelper(context, "cashbook.db", null, 1) {

    companion object {
        val tableName = "cashbook"
        private  var instance :CashDBHelper? = null;

        fun getInstance(context: Context):CashDBHelper{
            return instance ?: CashDBHelper(context.applicationContext)!!
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //データベースがないときに実行される
        db?.execSQL("create table cashbook ( " +
                "_id integer primary key autoincrement, " +
                "date text not null, " +
                "item text not null, " +
                "amount text not null " +
                ");")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //バージョンアップしたときに実行される
        //テーブルのdeleteなどを行う
    }
}