package com.xenovis.planszowki.screen.main.fragment.search.command

import com.xenovis.planszowki.mvp.UICommand
import com.xenovis.planszowki.screen.main.fragment.search.SearchView

/**
 * Created by maciek on 03/12/16.
 */

class ShowDetailsActivityCommand(val name: String) : UICommand<SearchView> {

    override fun execute(view: SearchView) {
        view.showDetailsActivity(name)
    }

}