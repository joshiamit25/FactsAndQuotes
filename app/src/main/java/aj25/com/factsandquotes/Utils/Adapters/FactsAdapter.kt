package aj25.com.factsandquotes.Utils.Adapters

import aj25.com.factsandquotes.R
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.facts_recyclerview_item.view.*

/**
 * Created by amit on 17/9/17.
 */
class FactsAdapter(val mContext : Context,val factsArray : ArrayList<String>) : RecyclerView.Adapter<FactsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.facts_recyclerview_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindData(factsArray[position])
    }

    override fun getItemCount(): Int {
        return factsArray.size
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(fact : String) {
            itemView.fact_tv.text = fact
        }
    }
}