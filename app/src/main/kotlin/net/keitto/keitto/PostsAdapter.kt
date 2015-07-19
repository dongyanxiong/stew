package net.keitto.keitto

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView

class PostsAdapter(private val context: Context) : RecyclerView.Adapter<PostsAdapter.PostViewHolder>(), PostsProvider.Listener {

    public class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val postImageView: SimpleDraweeView

        init {
            postImageView = itemView.findViewById(R.id.postImageView) as SimpleDraweeView
        }

    }

    private var posts: List<Post>? = null

    init {
        PostsProvider(this).loadPosts()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): PostViewHolder {
        val view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.post, viewGroup, false)

        return PostViewHolder(view)
    }

    override fun onBindViewHolder(postViewHolder: PostViewHolder, i: Int) {
        val post = posts!!.get(i)

        val controller = Fresco.newDraweeControllerBuilder().setUri(post.uri).setAutoPlayAnimations(true).build()
        postViewHolder.postImageView.setController(controller)
    }

    override fun getItemCount(): Int {
        return posts?.size() ?: 0
    }

    override fun onPostsLoaded(posts: Collection<Post>) {
        this.posts = posts.toList()

        notifyDataSetChanged()
    }

}
