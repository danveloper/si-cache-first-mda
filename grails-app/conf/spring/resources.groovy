import org.springframework.cache.concurrent.ConcurrentMapCache

// Place your Spring DSL code here
beans = {

  cache(ConcurrentMapCache, "post-items")
}
