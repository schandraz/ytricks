import org.codehaus.groovy.runtime.*;
def file='tail -n 500 ../../../../log/tomcat/console-'+DateGroovyMethods.format(new Date(), 'yyyyMMdd')+'.log'
println "${file}".execute().text