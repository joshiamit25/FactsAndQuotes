package aj25.com.factsandquotes.Utils.Adapters

import aj25.com.factsandquotes.R
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.news_recyclerview_item.view.*
import kotlinx.android.synthetic.main.quotes_recyclerview_item.view.*

/**
 * Created by amit on 17/9/17.
 */
class NewsAdapter(val mContext : Context,val newsTitleArray : ArrayList<String>,val newsBodyArray : ArrayList<String>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.news_recyclerview_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindData(newsTitleArray[position],newsBodyArray[position])
    }

    override fun getItemCount(): Int {
        return newsTitleArray.size
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(newsTitle : String,newsBody : String) {
            itemView.news_title_tv.text = newsTitle
            itemView.news_body_tv.text = newsBody
        }
    }
}