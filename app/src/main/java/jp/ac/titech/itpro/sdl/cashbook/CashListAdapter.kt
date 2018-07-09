package jp.ac.titech.itpro.sdl.cashbook

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import android.graphics.Color
import org.jetbrains.anko.layoutInflater
import org.w3c.dom.Text

class CashListAdapter : ArrayAdapter<ListData> {

    constructor(context: Context?, resource: Int) : super(context, resource)
    constructor(context: Context?, resource: Int, textViewResourceId: Int) : super(context, resource, textViewResourceId)
    constructor(context: Context?, resource: Int, objects: Array<out ListData>?) : super(context, resource, objects)
    constructor(context: Context?, resource: Int, textViewResourceId: Int, objects: Array<out ListData>?) : super(context, resource, textViewResourceId, objects)
    constructor(context: Context?, resource: Int, objects: MutableList<ListData>?) : super(context, resource, objects)
    constructor(context: Context?, resource: Int, textViewResourceId: Int, objects: MutableList<ListData>?) : super(context, resource, textViewResourceId, objects)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var newView = convertView ?: context.layoutInflater.inflate(R.layout.row, null)

        getItem(position)?.run {
            var result_amount = ""
            if(amount.indexOf("-") == -1) {
                newView.findViewById<LinearLayout>(R.id.rowList).setBackgroundColor(Color.rgb(200, 200, 255))
                result_amount = amount
            } else {
                newView.findViewById<LinearLayout>(R.id.rowList).setBackgroundColor(Color.rgb(255, 200, 200))
                result_amount = amount.substring(1)
            }
            newView.findViewById<TextView>(R.id.rowDate).text = date
            newView.findViewById<TextView>(R.id.rowItem).text = item
            newView.findViewById<TextView>(R.id.rowAmount).text = result_amount
        }
        return newView
    }
}
