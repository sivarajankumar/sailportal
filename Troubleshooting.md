# startup error #
problem:
you see this error when you're starting up the server:
```
Sep 1, 2010 4:43:27 PM org.apache.catalina.core.AprLifecycleListener init
INFO: The APR based Apache Tomcat Native library which allows optimal performance in production environments was not found on the java.library.path: .:/Library/Java/Extensions:/System/Library/Java/Extensions:/usr/lib/java
Sep 1, 2010 4:43:27 PM org.apache.tomcat.util.digester.SetPropertiesRule begin
WARNING: [SetPropertiesRule]{Server/Service/Engine/Host/Context} Setting property 'source' to 'org.eclipse.jst.j2ee.server:vlewrapper' did not find a matching property.
Sep 1, 2010 4:43:27 PM org.apache.tomcat.util.digester.SetPropertiesRule begin
WARNING: [SetPropertiesRule]{Server/Service/Engine/Host/Context} Setting property 'source' to 'org.eclipse.jst.j2ee.server:webapp' did not find a matching property.
Sep 1, 2010 4:43:27 PM org.apache.coyote.http11.Http11Protocol init
INFO: Initializing Coyote HTTP/1.1 on http-8080
Sep 1, 2010 4:43:27 PM org.apache.catalina.startup.Catalina load
INFO: Initialization processed in 888 ms
Sep 1, 2010 4:43:27 PM org.apache.catalina.core.StandardService start
INFO: Starting service Catalina
Sep 1, 2010 4:43:27 PM org.apache.catalina.core.StandardEngine start
INFO: Starting Servlet Engine: Apache Tomcat/6.0.29
Sep 1, 2010 4:43:29 PM org.apache.catalina.loader.WebappClassLoader validateJarFile
INFO: validateJarFile(/var/folders/W0/W0OS++zeFRKh-W1DsWxhV++++TI/-Tmp-/1-vlewrapper/WEB-INF/lib/servlet-api-2.5.jar) - jar not loaded. See Servlet Spec 2.3, section 9.7.2. Offending class: javax/servlet/Servlet.class
Sep 1, 2010 4:43:31 PM org.apache.catalina.core.StandardContext listenerStart
SEVERE: Error configuring application listener of class org.springframework.web.util.Log4jConfigListener
java.lang.ClassNotFoundException: org.springframework.web.util.Log4jConfigListener
	at org.apache.catalina.loader.WebappClassLoader.loadClass(WebappClassLoader.java:1645)
	at org.apache.catalina.loader.WebappClassLoader.loadClass(WebappClassLoader.java:1491)
	at org.apache.catalina.core.StandardContext.listenerStart(StandardContext.java:4078)
	at org.apache.catalina.core.StandardContext.start(StandardContext.java:4630)
	at org.apache.catalina.core.ContainerBase.start(ContainerBase.java:1045)
	at org.apache.catalina.core.StandardHost.start(StandardHost.java:785)
	at org.apache.catalina.core.ContainerBase.start(ContainerBase.java:1045)
	at org.apache.catalina.core.StandardEngine.start(StandardEngine.java:445)
	at org.apache.catalina.core.StandardService.start(StandardService.java:519)
	at org.apache.catalina.core.StandardServer.start(StandardServer.java:710)
	at org.apache.catalina.startup.Catalina.start(Catalina.java:581)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
	at java.lang.reflect.Method.invoke(Method.java:597)
	at org.apache.catalina.startup.Bootstrap.start(Bootstrap.java:289)
	at org.apache.catalina.startup.Bootstrap.main(Bootstrap.java:414)
Sep 1, 2010 4:43:31 PM org.apache.catalina.core.StandardContext listenerStart
SEVERE: Error configuring application listener of class net.sf.sail.webapp.spring.impl.CustomContextLoaderListener
java.lang.NoClassDefFoundError: org/springframework/web/context/ContextLoaderListener
	at java.lang.ClassLoader.defineClass1(Native Method)
	at java.lang.ClassLoader.defineClass(ClassLoader.java:700)
	at java.security.SecureClassLoader.defineClass(SecureClassLoader.java:124)
	at org.apache.catalina.loader.WebappClassLoader.findClassInternal(WebappClassLoader.java:2733)
	at org.apache.catalina.loader.WebappClassLoader.findClass(WebappClassLoader.java:1124)
	at org.apache.catalina.loader.WebappClassLoader.loadClass(WebappClassLoader.java:1612)
	at org.apache.catalina.loader.WebappClassLoader.loadClass(WebappClassLoader.java:1491)
	at org.apache.catalina.core.StandardContext.listenerStart(StandardContext.java:4078)
	at org.apache.catalina.core.StandardContext.start(StandardContext.java:4630)
	at org.apache.catalina.core.ContainerBase.start(ContainerBase.java:1045)
	at org.apache.catalina.core.StandardHost.start(StandardHost.java:785)
	at org.apache.catalina.core.ContainerBase.start(ContainerBase.java:1045)
	at org.apache.catalina.core.StandardEngine.start(StandardEngine.java:445)
	at org.apache.catalina.core.StandardService.start(StandardService.java:519)
	at org.apache.catalina.core.StandardServer.start(StandardServer.java:710)
	at org.apache.catalina.startup.Catalina.start(Catalina.java:581)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
	at java.lang.reflect.Method.invoke(Method.java:597)
	at org.apache.catalina.startup.Bootstrap.start(Bootstrap.java:289)
	at org.apache.catalina.startup.Bootstrap.main(Bootstrap.java:414)
Caused by: java.lang.ClassNotFoundException: org.springframework.web.context.ContextLoaderListener
	at org.apache.catalina.loader.WebappClassLoader.loadClass(WebappClassLoader.java:1645)
	at org.apache.catalina.loader.WebappClassLoader.loadClass(WebappClassLoader.java:1491)
	at java.lang.ClassLoader.loadClassInternal(ClassLoader.java:399)
	... 22 more
Sep 1, 2010 4:43:31 PM org.apache.catalina.core.StandardContext listenerStart
SEVERE: Error configuring application listener of class org.springframework.security.ui.session.HttpSessionEventPublisher
java.lang.ClassNotFoundException: org.springframework.security.ui.session.HttpSessionEventPublisher
	at org.apache.catalina.loader.WebappClassLoader.loadClass(WebappClassLoader.java:1645)
	at org.apache.catalina.loader.WebappClassLoader.loadClass(WebappClassLoader.java:1491)
	at org.apache.catalina.core.StandardContext.listenerStart(StandardContext.java:4078)
	at org.apache.catalina.core.StandardContext.start(StandardContext.java:4630)
	at org.apache.catalina.core.ContainerBase.start(ContainerBase.java:1045)
	at org.apache.catalina.core.StandardHost.start(StandardHost.java:785)
	at org.apache.catalina.core.ContainerBase.start(ContainerBase.java:1045)
	at org.apache.catalina.core.StandardEngine.start(StandardEngine.java:445)
	at org.apache.catalina.core.StandardService.start(StandardService.java:519)
	at org.apache.catalina.core.StandardServer.start(StandardServer.java:710)
	at org.apache.catalina.startup.Catalina.start(Catalina.java:581)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
	at java.lang.reflect.Method.invoke(Method.java:597)
	at org.apache.catalina.startup.Bootstrap.start(Bootstrap.java:289)
	at org.apache.catalina.startup.Bootstrap.main(Bootstrap.java:414)
Sep 1, 2010 4:43:31 PM org.apache.catalina.core.StandardContext listenerStart
SEVERE: Skipped installing application listeners due to previous error(s)
Sep 1, 2010 4:43:31 PM org.apache.catalina.core.StandardContext start
SEVERE: Error listenerStart
Sep 1, 2010 4:43:31 PM org.apache.catalina.core.StandardContext start
SEVERE: Context [/webapp] startup failed due to previous errors
```

cause:
It's likely that your eclipse configuration (ie class-loading lookup paths like org.eclipse.wst.common.component or .profile file) changed without you knowing, or your tomcat publishing is not synchronized.

solution #1:
  1. delete tomcat server
  1. add tomcat server and re-add the projects to the server.
  1. restart server.

solution #2 (if solution #1 doesn't work):
  1. synchronize your webapp checkout. Right click on webapp project folder, Team->Synchronize....
  1. see if you see any files that eclipse has changed without your knowledge, like .settings/org.eclipse.jdt.core.prefs, .settings/org.eclipse.wst.common.component,.classpath or .project
  1. If yes, revert those changes. Right-click on the file and select "override and update". clean project. Project->Clean. run "mvn clean; mvn compile; mvn resources:resources". Refresh project in Eclipse. Restart the server.
  1. If not, contact us in the googlecode mailinglist.