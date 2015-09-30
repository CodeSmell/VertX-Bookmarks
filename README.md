# VertX-Bookmarks
This project was created as a place to learn ([Vert.x](http://vertx.io/)), an event driven and non blocking framework for creating reactive applications. Vert.x runs on the JVM and its architecture and approach are based on the Reactor pattern, similar to Node.js.

If you are wondering about Vert.x, there is an interview with creator Tim Fox on ([InfoQ](http://www.infoq.com/articles/vertx-3-tim-fox)). There is also a good writeup in ([JavaWorld](http://www.javaworld.com/article/2078838/mobile-java/open-source-java-projects-vert-x.html)), though it covers version 2.

This version is built using Java 8 and Maven, but Vert.x can support various JVM languages

The ([Reactive Manifesto](http://www.reactivemanifesto.org/)) defines reactive applications as "systems that are Responsive, Resilient, Elastic and Message Driven."

This project builds the Bookmarks REST services found in chapter 1 of the Pragmatic Programmers book *Seven Web Frameworks in Seven Weeks*. The original services in the book were built using Sinatra and Ruby. 

It also relied on the Vert.x Core Manual and the Intro series
- ([Core Manual](http://vertx.io/docs/vertx-core/java/))
- ([My First Vert.x app] (http://vertx.io/blog/my-first-vert-x-3-application/index.html))
- ([Vert.x App Configuration] (http://vertx.io/blog/vert-x-application-configuration/index.html))
- ([Some REST with Vert.x] (http://vertx.io/blog/some-rest-with-vert-x/index.html))
- ([Unit and Integration tests] (http://vertx.io/blog/unit-and-integration-tests/index.html))

## Handling Objects
One challenge when I started working on the project was how to manage dependencies. After working with IoC frameworks, it seemed like a step backward to start doing this:

	private BookmarkDao bookmarksDao = new BookmarkNoDatabaseDao();
	
Therefore I chose to use Spring IoC to manage dependencies but that is not required for Vert.x applications. 

## Handling Data Access
The other challenge in this project was working through how to access data. While using MongoDB is recommended, I figured that the original Bookmarks project in the PragProg book used a RDBMS. And, let's face it most projects are using SQL databases. Having already chosen to manage dependencies with Spring, I chose to use MyBatis and Spring to handle the data access.

## The User Interface and Verticles
I thought it would be helpful to add a simple Web UI for the bookmarks. I was originally trying to do this with two Verticles. One Verticle would handle the REST API and the other Verticle would serve the static resources. 

	public class BookmarksVerticle {
		...
		public void start(Future<Void> future) {
			...
			
			router.get(BOOKMARK_URL).handler(this::getAllBookmarks);
			
			...
			
			vertx.createHttpServer().requestHandler(router::accept).listen(8080, ...

and 

	public class BookmarksStaticWebVerticle {
		...
		public void start(Future<Void> future) {
			...
			
			router.route(BOOKMARK_URL + "/static/*").handler(StaticHandler.create());
			
			...
			
			vertx.createHttpServer().requestHandler(router::accept).listen(8080, ...


But when I deployed them both I got 

	SEVERE: Address already in use: bind
	java.net.BindException: Address already in use: bind

I found out that I can't do that unless I started the HTTP listener on a different port. 

The other option was to create a third Verticle to listen for HTTP requests, parse them based on the path and send the requests to the REST Verticle and Static Web Verticle over the over the event bus. My original two Verticles would then listen for these requests over the event bus instead of HTTP. 

This might make for a good refactor later. 

## Running from Maven
Run the following goals: 
	
	mvn clean package

Then run the "fat" JAR file
	
	java -jar target/Bookmarks-0.0.1-SNAPSHOT-fat.jar

## Testing Bookmarks
Once the application is running you can test the REST API using various tools (SOAP UI, curl etc). 
The easiest way is to use a browser and access one of the REST API that uses the GET method 
		
	http://localhost:8080/bookmarks

This should return a JSON array:

	[ {
	  "bookmarkId" : "1",
	  "bookmarkUrl" : "http://vertx.io",
	  "bookmarkTitle" : "Vert.x Reactive Framework"
	}, {
	  "bookmarkId" : "2",
	  "bookmarkUrl" : "http://typesafe.com/",
	  "bookmarkTitle" : "Typesafe Reactive Framework"
	} ]
