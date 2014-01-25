package app

/**
 * User: danielwoods
 */
class PostController {
  def newPostPipeline

  def create(Post post) {
    post.validate()
    if (!post.hasErrors()) {
      newPostPipeline.save(post)
      render status: 204
    } else {
      render status: 400
    }
  }
}
