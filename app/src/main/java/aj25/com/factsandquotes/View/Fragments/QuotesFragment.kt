package aj25.com.factsandquotes

import aj25.com.factsandquotes.Utils.Adapters.QuotesAdapter
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.quotes_fragment_layout.*
import org.json.JSONObject

/**
 * Created by amit on 17/9/17.
 */
class QuotesFragment : Fragment() {
    private val BASE_URL = "http://utility-api.herokuapp.com/api/"
    private val QUOTE_URL = BASE_URL + "quote/quoteoftheday"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.quotes_fragment_layout,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showView(progressbar)
        quotes_recycler_view.layoutManager = LinearLayoutManager(activity)
        getQuotes(QUOTE_URL)

        refresh.setOnClickListener {
            refresh()
        }
        about.setOnClickListener {
            showAboutDialog()
        }
    }

    private fun hideView(view : View) {
        view.visibility = View.GONE
    }
    private fun showView(view : View) {
        view.visibility = View.VISIBLE
    }
    private fun getQuotes(url : String?) {
        var quotes = ArrayList<String>()
        var queue = Volley.newRequestQueue(activity)
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener<JSONObject> { response ->

            val quotesArray  = response.optJSONArray("message")
            Log.d("",quotesArray.toString())
            for (i in 0..(quotesArray.length() - 1)) {
                val quoteMessage = quotesArray[i].toString()
                quotes.add(quoteMessage)
            }
            val adapter = QuotesAdapter(activity,quotes)

            if (quotes_recycler_view != null) {
                quotes_recycler_view.adapter = adapter
                hideView(progressbar)
                showView(main_content)
            }

        }, Response.ErrorListener { error ->
            toast(error.message.toString())

        })
        queue.add(jsonObjectRequest)
    }
    private fun toast(message : String) {
        Toast.makeText(activity,message,Toast.LENGTH_LONG).show()
    }
    private fun refresh() {
        hideView(main_content)
        showView(progressbar)
        getQuotes(QUOTE_URL)
    }
    private fun showAboutDialog() {
        val dialog = AlertDialog.Builder(activity).create()
        dialog.setTitle(getString(R.string.about))
        dialog.setMessage(getString(R.string.dialog_message))
        dialog.show()
    }
}