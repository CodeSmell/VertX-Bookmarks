package codesmell.vertx.pragprog.exceptions;

public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = 6800785022666156534L;

	public NotFoundException() {
		super();
	}

	public NotFoundException(String msg) {
		super(msg);
	}
}
