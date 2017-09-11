package aj25.com.factsandquotes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    val BASE_URL = "http://utility-api.herokuapp.com/api/"
    val QUOTE_URL = BASE_URL + "quote/quoteoftheday"
    val FACT_URL = BASE_URL + "fact/factoftheday"
    val JOKE_URL = BASE_URL + "joke/jokeoftheday"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showView(progressbar)
        hideView(main_content)
        getQuotes(QUOTE_URL)
        getFacts(FACT_URL)

        info.setOnClickListener {
            val dialog = AlertDialog.Builder(this).create()
            dialog.setTitle(getString(R.string.about))
            dialog.setMessage(getString(R.string.dialog_message))
            dialog.show()
        }

        refresh.setOnClickListener {
            toast(getString(R.string.refresh))
            getQuotes(QUOTE_URL)
            getFacts(FACT_URL)
        }

    }
    private fun hideView(view : View) {
        view.visibility = View.GONE
    }
    private fun showView(view : View) {
        view.visibility = View.VISIBLE
    }
    fun getQuotes(url : String?) {
        var queue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET,url,null,Response.Listener<JSONObject> {
            response ->
            hideView(progressbar)
            showView(main_content)
            val quotesArray = response.optJSONArray("message")
          /**  for (i in 0..(quotesArray.length()-1)) {
                allQuotes =  allQuotes + quotesArray[i]
            } **/
            quotes.text = quotesArray[0].toString()
        },Response.ErrorListener{
            error ->
            toast(error.message.toString())

        })
        queue.add(jsonObjectRequest)
    }
    fun getFacts(url : String?) {
        var queue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET,url,null,Response.Listener<JSONObject> {
            response ->
            hideView(progressbar)
            showView(main_content)
            val factsArray = response.optJSONArray("message")
            facts.text = factsArray[0].toString()

        },Response.ErrorListener{
            error ->
            toast(error.message.toString())

        })
        queue.add(jsonObjectRequest)
    }
    fun toast(message : String) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }
}
