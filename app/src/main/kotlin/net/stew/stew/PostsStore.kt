package net.stew.stew

import java.util.HashMap

class PostsStore {

    private val postsByCollection = HashMap(PostCollection.Predefined.map { it to arrayListOf<Post>() }.toMap())

    fun store(collection: PostCollection, posts: Collection<Post>) {
        postsByCollection[collection].clear()
        postsByCollection[collection].addAll(posts)
    }

    fun restore(collection: PostCollection) = postsByCollection[collection].toList()

    fun clear() {
        postsByCollection.forEach { it.value.clear() }
    }
}