package com.xenovis.planszowki.screen.main.fragment.categories

import com.xenovis.planszowki.data.api.manager.ApiManager
import com.xenovis.planszowki.mvp.BasePresenter
import com.xenovis.planszowki.screen.main.activity.bottom_navigation.FragNavController
import com.xenovis.planszowki.screen.main.fragment.categories.categories.CategoriesListener
import com.xenovis.planszowki.screen.main.fragment.categories.command.ShowCategoriesCommand
import com.xenovis.planszowki.screen.main.fragment.categorised_games.CategorisedGamesFragment
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by maciek on 03/12/16.
 */
class CategoriesPresenter : BasePresenter<CategoriesView>() {
    override fun onFirstViewAttachment() {
        println("COS NIE DZIALA")
        ApiManager.instance
                .getAllCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ( { executeOnce(ShowCategoriesCommand(it, getListener())) },
                        {error ->})
    }

    private fun getListener(): CategoriesListener {
        return object : CategoriesListener {
            override fun onClicked(category: String) {
                FragNavController.instance.push(CategorisedGamesFragment.newInstance(category))
            }
        }
    }
}
