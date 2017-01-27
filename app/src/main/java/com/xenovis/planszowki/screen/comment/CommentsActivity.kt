package com.xenovis.planszowki.screen.comment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.xenovis.planszowki.R
import com.xenovis.planszowki.data.api.response.Comment
import com.xenovis.planszowki.mvp.BaseActivity
import com.xenovis.planszowki.screen.comment.comment.CommentsAdapter
import com.xenovis.planszowki.screen.comment.dialog.CommentDialogFragment
import com.xenovis.planszowki.screen.comment.dialog.CommentListener
import com.xenovis.planszowki.screen.main.fragment.ranking.games.CategoriesItemDecoration
import kotlinx.android.synthetic.main.activity_comments.*

class CommentsActivity : BaseActivity<CommentsPresenter, CommentsView>(), CommentsView {

    override val presenter = CommentsPresenter()
    override val layout = R.layout.activity_comments

    companion object {
        private val NAME_KEY = "NAME"
        private val LOGO_KEY = "LOGO"

        fun newStartIntent(context: Context, name: String, logo: String?) : Intent {
            val intent = Intent(context, CommentsActivity::class.java)
            intent.putExtra(NAME_KEY, name)
            intent.putExtra(LOGO_KEY, logo)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name = intent.getStringExtra(NAME_KEY)
        val logo = intent.getStringExtra(LOGO_KEY)

        toolbar.title = getString(R.string.comments)
        presenter.onCreate(name, logo)
        comment_fab.setOnClickListener { presenter.onFabClicked() }
    }

    override fun showName(name: String) {
        game_name_textview.text = name
    }

    override fun showLogo(logoUrl: String) {
        Picasso.with(this).load(logoUrl).into(game_logo_imageview)
    }

    override fun showComments(list: List<Comment>) {
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.addItemDecoration(CategoriesItemDecoration(this))
        recycler_view.adapter = CommentsAdapter(list)
    }

    override fun showCommentDialog(listener: CommentListener) {
        val dialog = CommentDialogFragment.newInstance(listener)
        dialog.show(supportFragmentManager, "comment_dialog")
    }
}
