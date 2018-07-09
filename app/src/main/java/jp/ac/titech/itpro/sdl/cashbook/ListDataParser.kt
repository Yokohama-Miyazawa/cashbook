package jp.ac.titech.itpro.sdl.cashbook

import org.jetbrains.anko.db.MapRowParser

class ListDataParser : MapRowParser<ListData> {
    override fun parseRow(columns: Map<String, Any?>): ListData {
        return ListData(columns["date"] as String, columns["item"] as String, columns["amount"] as String)
    }
}