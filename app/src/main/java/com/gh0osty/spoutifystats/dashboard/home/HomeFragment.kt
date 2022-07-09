package com.gh0osty.spoutifystats.dashboard.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.gh0osty.spoutifystats.auth.pojos.UserPojo
import com.gh0osty.spoutifystats.R
import de.hdodenhof.circleimageview.CircleImageView
import io.paperdb.Paper
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.facebook.shimmer.ShimmerFrameLayout


class HomeFragment : Fragment() {

    private var spotifyLink: CircleImageView? = null
    private var userNameView: TextView? = null
    private var shimmerName: ShimmerFrameLayout? = null
    private val user = Paper.book().read<UserPojo>("User")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val mainView: View = inflater.inflate(R.layout.fragment_home, container, false)
        loadAll(mainView)

        return mainView
    }

    private fun loadAll(view: View) {
        spotifyLink = view.findViewById(R.id.home_userLink)
        userNameView = view.findViewById(R.id.home_userName)
        shimmerName = view.findViewById(R.id.home_userImageFrame)
        shimmerName?.startShimmer()

        if (user != null) {
            spotifyLink?.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(user.link)))
            }


            Glide.with(this).load(user.image).fitCenter().placeholder(R.drawable.blank)
                .error(R.drawable.spotify)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        shimmerName?.setShimmer(null)
                        userNameView?.text = user.name
                        return false
                    }

                })
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view.findViewById(R.id.home_userImage))


        }

    }

}