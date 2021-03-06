package com.xenovis.planszowki.screen.main.activity.command

import com.xenovis.planszowki.mvp.UICommand
import com.xenovis.planszowki.screen.main.activity.MainView

/**
 * Created by maciek on 03/12/16.
 */
class ShowInitialFragmentForCurrentTabIfNeededCommand(val shouldClearStack: Boolean) : UICommand<MainView> {

    override fun execute(view: MainView) {
        if (shouldClearStack) view.clearStackForCurrentTab()
    }
}