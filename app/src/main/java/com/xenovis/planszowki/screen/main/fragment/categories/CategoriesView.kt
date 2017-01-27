package com.xenovis.planszowki.screen.main.fragment.categories

import com.xenovis.planszowki.data.api.response.Category
import com.xenovis.planszowki.mvp.BaseView
import com.xenovis.planszowki.screen.main.fragment.categories.categories.CategoriesListener

/**
 * Created by maciek on 03/12/16.
 */

interface CategoriesView : BaseView {
    fun showCategories(list: List<Category>, listener: CategoriesListener)
}