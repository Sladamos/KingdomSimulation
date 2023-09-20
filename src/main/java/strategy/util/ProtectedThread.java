package strategy.util;

import lombok.Setter;
import strategy.error.ErrorHandler;

public class ProtectedThread extends Thread {

	@Setter
	private static ErrorHandler errorHandler;

	public ProtectedThread(Runnable target) {
		super(() -> {
			if(errorHandler != null) {
				errorHandler.runInErrorHandler(target);
			} else {
				target.run();
			}
		});
	}
}
