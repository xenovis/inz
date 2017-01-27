package com.xenovis.planszowki.screen.start

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.xenovis.planszowki.R
import com.xenovis.planszowki.mvp.BaseActivity
import com.xenovis.planszowki.screen.main.activity.MainActivity
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : BaseActivity<StartPresenter, StartView>(), StartView {
    override val presenter = StartPresenter()

    override val layout = R.layout.activity_start

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        assignListeners()
    }

    private fun assignListeners() {
        login_button.setOnClickListener {
            presenter.loginClicked(
                    username_edit_text.text.toString(),
                    password_edit_text.text.toString()
            )
        }
        register_button.setOnClickListener {
            presenter.registerClicked(
                    username_edit_text.text.toString(),
                    password_edit_text.text.toString(),
                    email_edit_text.text.toString(),
                    firstname_edit_text.text.toString(),
                    lastname_edit_text.text.toString()
            )
        }
        guest_button.setOnClickListener { presenter.guestClicked() }
    }

    override fun changeRegisterSectionVisibility(visible: Boolean) {
        register_section.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun showMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
