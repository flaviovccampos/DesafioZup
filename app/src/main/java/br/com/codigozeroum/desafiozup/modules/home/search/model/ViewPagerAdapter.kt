package br.com.codigozeroum.desafiozup.modules.home.search.model

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fragManager: FragmentManager, private val fragments: List<Fragment>) : FragmentPagerAdapter(fragManager) {

    override fun getItem(position: Int): Fragment = fragments[position]
    override fun getCount(): Int = fragments.size
    override fun getPageTitle(position: Int): CharSequence = ""

}