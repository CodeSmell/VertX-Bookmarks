package vertx.hello;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import vertx.hello.HttpHelloVerticle;

@RunWith(VertxUnitRunner.class)
public class HttpHelloVerticleTest {
	
	int port = 8888;
	private Vertx vertx;

	@Before
	public void setup(TestContext context) {
		vertx = Vertx.vertx();
	
		DeploymentOptions options = new DeploymentOptions().setConfig(new JsonObject().put("http.port", port));
		vertx.deployVerticle(HttpHelloVerticle.class.getName(), options, context.asyncAssertSuccess());
	}

	@After
	public void tearDown(TestContext context) {
		vertx.close(context.asyncAssertSuccess());
	}

	@Test
	public void test_HelloWorld(TestContext context) {
		final Async async = context.async();
		
		vertx.createHttpClient().getNow(port, "localhost", "/", response -> {
			response.handler(body->{
		        context.assertTrue(body.toString().contains("Hello"));
		        async.complete();
			});
		});
	}
}
