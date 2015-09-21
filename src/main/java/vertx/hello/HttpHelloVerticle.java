package vertx.hello;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;

/**
 * Verticles are chunks of code that get deployed and run by Vert.x but they are
 * not the only way to create apps.
 *
 * Standard Verticles are assigned an event loop thread when they are created
 */
public class HttpHelloVerticle extends AbstractVerticle {

	public static final String HELLO_HTML = "<h1>Hello World from Vert.x 3</h1>";

	/**
	 * Called when verticle is deployed 
	 * <br>
	 * Verticle is in the started state when
	 * this method calls complete on Future
	 */
	@Override
	public void start(Future<Void> fut) {
		// creates an HTTP server
		// and attaches a request handler to it
		HttpServer httpSrvr = vertx.createHttpServer();

		// return some simple Hello World HTML
		// whenever an HTTP request event occurs
		httpSrvr.requestHandler(request -> { request.response().end(HELLO_HTML); });

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