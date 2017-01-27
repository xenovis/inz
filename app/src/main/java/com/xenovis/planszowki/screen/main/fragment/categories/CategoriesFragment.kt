package com.xenovis.planszowki.screen.main.fragment.categories

import android.support.v7.widget.LinearLayoutManager
import com.xenovis.planszowki.R
import com.xenovis.planszowki.data.api.response.Category
import com.xenovis.planszowki.mvp.BaseFragment
import com.xenovis.planszowki.screen.main.fragment.categories.categories.CategoriesAdapter
import com.xenovis.planszowki.screen.main.fragment.categories.categories.CategoriesListener
import com.xenovis.planszowki.screen.main.fragment.ranking.games.CategoriesItemDecoration
import kotlinx.android.synthetic.main.fragment_categories.*

/**
 * Created by maciek on 03/12/16.
 */

class CategoriesFragment : BaseFragment<CategoriesPresenter, CategoriesView>(), CategoriesView{
    override val presenter = CategoriesPresenter()
    override val layout = R.layout.fragment_categories

    override fun setupToolbarLayout() {
        toolbar.title = getString(R.string.categories)
    }

    override fun showCategories(list: List<Category>, listener: CategoriesListener) {
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = CategoriesAdapter(list, listener)
        recycler_view.addItemDecoration(CategoriesItemDecoration(context))
        println("list = [$list]")
    }
}

