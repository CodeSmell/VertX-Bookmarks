package vertx.hello;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

public class WebHelloVerticle extends AbstractVerticle {
	public static final String HELLO_HTML = "<h1>Hello World from Vert.x Web</h1>";

	@Override
	public void start(Future<Void> fut) {
		// creates an Router
		Router router = Router.router(vertx);

		// bind the router to URL /helloweb
		router.route("/helloweb").handler(routingContext -> {
			HttpServerResponse response = routingContext.response();
			response.putHeader("content-type", "text/html").end(HELLO_HTML);
		});
		
		// creates an HTTP server
		// and attaches a request handler that calls the Router accept method
		HttpServer httpSrvr = vertx.createHttpServer().requestHandler(router::accept);

		// start up the HTTP server
		httpSrvr.listen(config().getInteger("http.port",8080), result -> {
			if (result.succeeded()) {
				fut.complete();
			}
			else {
				fut.fail(result.cause());
			}
		});
	}
}
