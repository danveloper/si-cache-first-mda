grails.servlet.version = "3.0"
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.work.dir = "target/work"
grails.project.target.level = 1.6
grails.project.source.level = 1.6

grails.project.dependency.resolver = "maven"
grails.project.dependency.resolution = {
  inherits("global") {}
  log "error"
  checksums true
  legacyResolve false
  repositories {
    inherits true
    grailsPlugins()
    grailsHome()
    mavenLocal()
    grailsCentral()
    mavenCentral()
  }
  dependencies {
    compile 'org.springframework.integration:spring-integration-core:3.0.0.RELEASE'
  }
  plugins {
    build ":jetty:2.0.3"
    compile ':cache:1.1.1'
    compile ":atmosphere:0.4.2.1"
    runtime ":hibernate:3.6.10.7"
    runtime ":jquery:1.10.2.2"
    runtime ":resources:1.2.1"
  }
}
