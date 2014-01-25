package app

import com.odelia.grails.plugins.atmosphere.StratosphereServlet
import grails.converters.JSON
import groovy.util.logging.Log4j
import org.atmosphere.cpr.Broadcaster
import org.springframework.scheduling.annotation.Scheduled

/**
 * User: danielwoods
 * Date: 1/25/14
 */
@Log4j
class PostService {
  static final WS_URI = "/atmosphere/ws"
  static atmosphere = [mapping: WS_URI]

  def cache
  def grailsApplication

  /**
   * Cache the post...
   */
  Post save(Post post) {
    cache.put post.content.encodeAsMD5(), post
    post
  }

  /**
   * Lazily save the post...
   */
  @Scheduled(fixedDelay = 10000l)
  void persistPosts() {
    if (cache.nativeCache.size()) {
      log.info "Storing posts..."
      try {
        Post.withNewTransaction {
          Post.saveAll(cache.nativeCache.values())
        }
        cache.clear()
        log.info "Done storing posts..."
      } catch (e) {
        log.error "Oops, there was a problem storing the posts!"
      }
    }
  }

  /**
   * Notify the clients...
   */
  Post notify(Post post) {
    def json = (post as JSON).toString(true)
    def future = broadcaster[WS_URI].broadcast(json).get()
    post
  }

  def onRequest = { event ->
    def request = event.request
    event.suspend()
  }

  def onStateChange = { event ->
    if (event.cancelled) {
      log.info "onStateChange, cancelling $event"
    }
    else if (event.message) {
      log.info "onStateChange, message: ${event.message}"
      def writer = event.resource.response.writer
      writer.write event.message
      writer.flush()
    }
  }

  private def getBroadcaster() {
    Map<String, Broadcaster> _broadcaster = [:]
    grailsApplication.mainContext.servletContext[StratosphereServlet.ATMOSPHERE_PLUGIN_HANDLERS_CONFIG].each {
      _broadcaster."${it.key}" = it.value.broadcaster
    }
    _broadcaster
  }
}
