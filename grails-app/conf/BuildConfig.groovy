import org.codehaus.groovy.grails.resolve.GrailsRepoResolver;

grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve

    repositories {
        inherits true // Whether to inherit repository definitions from plugins
        grailsPlugins()
        grailsHome()
        grailsCentral()
        mavenCentral()

        // uncomment these to enable remote dependency resolution from public Maven repositories
        //mavenCentral()
        //mavenLocal()
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
		
		/**
		 * Configure our resolver.
		 */
//		def libResolver = new GrailsRepoResolver(null, null);
//		libResolver.addArtifactPattern("https://github.com/fterrier/repository/raw/master/[organisation]/[module]/[type]s/[artifact]-[revision].[ext]")
//		libResolver.addIvyPattern("https://github.com/fterrier/repository/raw/master/[organisation]/[module]/ivys/ivy-[revision].xml")
//		libResolver.name = "github"
////		libResolver.settings = ivySettings
//		resolver libResolver
		
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.
		// because of GRAILS-6147, this dependency is in lib instead of here
		//compile group: "net.sf.json-lib", name: "json-lib", version: "2.4", classifier: "jdk15"
		
		compile 'net.sf.ezmorph:ezmorph:1.0.6'
		runtime 'mysql:mysql-connector-java:5.1.5'
		compile 'commons-lang:commons-lang:2.6'
    }

    plugins {
        runtime ":hibernate:$grailsVersion"
		runtime ":mail:1.0"
        runtime ":jquery:1.7.1"
        runtime ":resources:1.2-RC1"
		compile ":jquery:1.7.1"
		compile ":resources:1.2-RC1"
		compile ":shiro:1.1.5"
		compile ":mail:1.0"
        build ":tomcat:$grailsVersion"
		test (":spock:0.6") {changing = false}
    }
}
