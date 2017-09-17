package aj25.com.factsandquotes

import aj25.com.factsandquotes.Utils.Adapters.ViewPagerAdapter
import aj25.com.factsandquotes.View.Fragments.FactsFragment
import aj25.com.factsandquotes.View.Fragments.JokesFragment
import aj25.com.factsandquotes.View.Fragments.NewsFragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpViewPager(viewPager)
        tab_layout.setupWithViewPager(viewPager)
    }

    private fun setUpViewPager(viewPager : ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(NewsFragment())
        adapter.addFragment(QuotesFragment())
        adapter.addFragment(FactsFragment())
        adapter.addFragment(JokesFragment())
        viewPager.adapter = adapter
    }
}
