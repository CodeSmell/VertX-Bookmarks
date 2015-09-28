package codesmell.vertx.hello;

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

import codesmell.vertx.hello.WebHelloVerticle;

@RunWith(VertxUnitRunner.class)
public class WebHelloVerticleTest {

	int port = 8888;
	private Vertx vertx;

	@Before
	public void setup(TestContext context) {
		vertx = Vertx.vertx();
	
		DeploymentOptions options = new DeploymentOptions().setConfig(new JsonObject().put("http.port", port));
		vertx.deployVerticle(WebHelloVerticle.class.getName(), options, context.asyncAssertSuccess());
	}

	@After
	public void tearDown(TestContext context) {
		vertx.close(context.asyncAssertSuccess());
	}

	@Test
	public void test_HelloWorld(TestContext context) {
		final Async async = context.async();
		
		vertx.createHttpClient().getNow(port, "localhost", "/helloweb", response -> {
			response.handler(body->{
				String stringBody = body.toString();
				System.out.println(stringBody);
		        context.assertTrue(stringBody.contains("Vert.x Web"));
		        async.complete();
			});
		});
	}
}
