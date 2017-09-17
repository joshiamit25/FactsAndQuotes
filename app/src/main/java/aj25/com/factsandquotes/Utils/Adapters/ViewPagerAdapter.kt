package aj25.com.factsandquotes.Utils.Adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by amit on 17/9/17.
 */
class ViewPagerAdapter(fragmentManager : FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    val mFragmentList = ArrayList<Fragment>()

    override fun getCount(): Int {
        return mFragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return  mFragmentList[position]
    }

    fun addFragment(fragment : Fragment) {
        mFragmentList.add(fragment)
    }
}