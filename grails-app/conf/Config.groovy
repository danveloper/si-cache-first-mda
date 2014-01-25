grails.project.groupId = "app"
grails.mime.disable.accept.header.userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
grails.mime.types = [
  all: '*/*',
  atom: 'application/atom+xml',
  css: 'text/css',
  csv: 'text/csv',
  form: 'application/x-www-form-urlencoded',
  html: ['text/html', 'application/xhtml+xml'],
  js: 'text/javascript',
  json: ['application/json', 'text/json'],
  multipartForm: 'multipart/form-data',
  rss: 'application/rss+xml',
  text: 'text/plain',
  hal: ['application/hal+json', 'application/hal+xml'],
  xml: ['text/xml', 'application/xml']
]
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']
grails.views.default.codec = "html"
grails.controllers.defaultScope = 'singleton'
grails {
  views {
    gsp {
      encoding = 'UTF-8'
      htmlcodec = 'xml'
      codecs {
        expression = 'html'
        scriptlet = 'html'
        taglib = 'none'
        staticparts = 'none'
      }
    }

    filteringCodecForContentType {

    }
  }
}
grails.converters.encoding = "UTF-8"
grails.scaffolding.templates.domainSuffix = 'Instance'
grails.json.legacy.builder = false
grails.enable.native2ascii = true
grails.spring.bean.packages = []
grails.web.disable.multipart = false
grails.exceptionresolver.params.exclude = ['password']
grails.hibernate.cache.queries = false
environments {
  development {
    grails.logging.jul.usebridge = true
  }
  production {
    grails.logging.jul.usebridge = false

  }
}
log4j = {
  info 'app'

  error 'org.codehaus.groovy.grails.web.servlet',
    'org.codehaus.groovy.grails.web.pages',
    'org.codehaus.groovy.grails.web.sitemesh',
    'org.codehaus.groovy.grails.web.mapping.filter',
    'org.codehaus.groovy.grails.web.mapping',
    'org.codehaus.groovy.grails.commons',
    'org.codehaus.groovy.grails.plugins',
    'org.codehaus.groovy.grails.orm.hibernate',
    'org.springframework',
    'org.hibernate',
    'net.sf.ehcache.hibernate'
}

grails.app.context = '/'