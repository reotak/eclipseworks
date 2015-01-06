/*
 * Copyright (C) 2012, 2013 RICOH Co., Ltd. All rights reserved.
 */

package threadPool;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Simple Thread Pool class.
 *
 * This class can be used to dispatch an Runnable object to be exectued by a
 * thread.
 *
 * [Instruction] Implement one constructor and three methods. Don't forget to
 * write a Test program to test this class. Pay attention to @throws tags in the
 * javadoc. If needed, you can put "synchronized" keyword to methods. All
 * classes for implementation must be private inside this class. Don't use
 * java.util.concurrent package.
 */
public class ThreadPool {
	private boolean isStart = false;

	private final Queue<Runnable> queue = new LinkedList<Runnable>();
	private final int maxQueueSize;

	private final ThreadRunner[] threads;

	/**
	 * Constructs ThreadPool.
	 *
	 * @param queueSize
	 *            the max size of queue
	 * @param numberOfThreads
	 *            the number of threads in this pool.
	 *
	 * @throws IllegalArgumentException
	 *             if either queueSize or numberOfThreads is less than 1
	 */
	public ThreadPool(int queueSize, int numberOfThreads) {
		if (queueSize < 1 || numberOfThreads < 1) {
			throw new IllegalArgumentException();
		}

		maxQueueSize = queueSize;

		threads = new ThreadRunner[numberOfThreads];

		for (int i = 0; i < numberOfThreads; i++) {
			threads[i] = new ThreadRunner();
		}
	}

	/**
	 * Starts threads.
	 *
	 * @throws IllegalStateException
	 *             if threads has been already started.
	 */
	public synchronized void start() {
		if (isStart) {
			throw new IllegalStateException();
		} else {
			isStart = true;
		}

		for (ThreadRunner thread : threads) {
			thread.start();
		}
	}

	/**
	 * Stop all threads and wait for their terminations.
	 *
	 * @throws IllegalStateException
	 *             if threads has not been started.
	 */
	public synchronized void stop() {
		if (!isStart) {
			throw new IllegalStateException();
		}
		isStart = false;

		for (;;) {
			boolean[] isThreadStopped = new boolean[threads.length];
			for (int i = 0; i < isThreadStopped.length; i++) {
				isThreadStopped[i] = false;
 			}

			for (int i = 0; i < threads.length; i++) {
				if (isThreadStopped[i] == true) {
 					continue;
				}
				threads[i].requireStop();
				synchronized(queue) {
					queue.notifyAll();
				}
				if (threads[i].isStopped()) {
					isThreadStopped[i] = true;
				}
			}

			boolean canEnd = true;
			for (int i = 0; i < threads.length; i++) {
				if (!isThreadStopped[i] || threads[i].isAlive()) {
					canEnd = false;
					break;
				}
			}

			if (canEnd) {
				break;
			}
		}
	}

	/**
	 * Executes the specified Runnable object, using a thread in the pool. run()
	 * method will be invoked in the thread. If the queue is full, then this
	 * method invocation will be blocked until the queue is not full.
	 *
	 * @param runnable
	 *            Runnable object whose run() method will be invoked.
	 *
	 * @throws NullPointerException
	 *             if runnable is null.
	 * @throws IllegalStateException
	 *             if this pool has not been started yet.
	 */
	public synchronized void dispatch(Runnable runnable) {
		if (runnable == null) {
			throw new NullPointerException();
		}
		if (!isStart) {
			throw new IllegalStateException();
		}

		synchronized(queue) {
			while (queue.size() > maxQueueSize) {
				try {
					queue.wait();
				} catch (InterruptedException e) {
				}
			}
			queue.add(runnable);
			queue.notifyAll();
		}
	}

	private class ThreadRunner extends Thread {
		private boolean isRequiredStop = false;
		private Boolean isStopped = false;
		private Runnable target = null;
		public void run() {
			for (;;) {
				synchronized(queue) {

					while (queue.isEmpty()) {
						try {
							if (isRequiredStop) {
								synchronized (isStopped) {
									isStopped = true;
									return;
								}
							}
							queue.wait();
						} catch (InterruptedException e) {
						}
					}
					target = queue.remove();
					queue.notifyAll();
				}
				target.run();
			}
		}

		public boolean isStopped() {
			synchronized(isStopped) {
				return isStopped;
			}
		}

		public void requireStop() {
			isRequiredStop = true;
		}

	}
}