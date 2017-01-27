package com.xenovis.planszowki.screen.start.command

import com.xenovis.planszowki.mvp.UICommand
import com.xenovis.planszowki.screen.start.StartView

/**
 * Created by maciek on 03/12/16.
 */
class ShowMainActivityCommand : UICommand<StartView>{
    override fun execute(view: StartView) {
        view.showMainActivity()
    }
}