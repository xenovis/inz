package com.xenovis.planszowki.screen.main.fragment.categories.command

import com.xenovis.planszowki.data.api.response.Category
import com.xenovis.planszowki.mvp.UICommand
import com.xenovis.planszowki.screen.main.fragment.categories.CategoriesView
import com.xenovis.planszowki.screen.main.fragment.categories.categories.CategoriesListener

/**
 * Created by maciek on 03/12/16.
 */

class ShowCategoriesCommand(val list: List<Category>, val listener: CategoriesListener) : UICommand<CategoriesView> {
    override fun execute(view: CategoriesView) {
        view.showCategories(list, listener)
    }

}