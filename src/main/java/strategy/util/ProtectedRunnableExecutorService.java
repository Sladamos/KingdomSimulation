package strategy.util;

import lombok.Setter;
import strategy.error.CriticalAppError;
import strategy.error.ErrorHandler;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class ProtectedRunnableExecutorService implements ExecutorService {

	@Setter
	private static ErrorHandler errorHandler;

	private final ExecutorService executorService;

	public ProtectedRunnableExecutorService() {
		executorService = Executors.newCachedThreadPool();
	}

	@Override
	public void shutdown() {
		executorService.shutdown();
	}

	@Override
	public List<Runnable> shutdownNow() {
		return executorService.shutdownNow();
	}

	@Override
	public boolean isShutdown() {
		return executorService.isShutdown();
	}

	@Override
	public boolean isTerminated() {
		return executorService.isTerminated();
	}

	@Override
	public boolean awaitTermination(long l, TimeUnit timeUnit) throws InterruptedException {
		return executorService.awaitTermination(l, timeUnit);
	}

	@Override
	public <T> Future<T> submit(Callable<T> callable) {
		throw new CriticalAppError("Callable is not supported!");
	}

	@Override
	public <T> Future<T> submit(Runnable runnable, T t) {
		return executorService.submit(runInErrorHandler(runnable), t);
	}

	@Override
	public Future<?> submit(Runnable runnable) {
		return executorService.submit(runInErrorHandler(runnable));
	}

	@Override
	public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) throws InterruptedException {
		throw new CriticalAppError("Callable is not supported!");
	}

	@Override
	public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long l, TimeUnit timeUnit) throws InterruptedException {
		throw new CriticalAppError("Callable is not supported!");
	}

	@Override
	public <T> T invokeAny(Collection<? extends Callable<T>> collection) throws InterruptedException, ExecutionException {
		throw new CriticalAppError("Callable is not supported!");
	}

	@Override
	public <T> T invokeAny(Collection<? extends Callable<T>> collection, long l, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
		throw new CriticalAppError("Callable is not supported!");
	}

	@Override
	public void execute(Runnable runnable) {
		executorService.execute(runInErrorHandler(runnable));
	}

	private Runnable runInErrorHandler(Runnable runnable) {
		return () -> errorHandler.runInErrorHandler(runnable);
	}
}
