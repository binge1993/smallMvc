package com.binge.smallmvc.wrapper;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;

/**
 *
 * @author binge
 * @date 2017年10月3日
 */

public class DelegatingServletInputStream extends ServletInputStream {

	private final InputStream sourceStream;

	private boolean finished = false;

	/**
	 * Create a DelegatingServletInputStream for the given source stream.
	 * 
	 * @param sourceStream
	 *            the source stream (never {@code null})
	 */
	public DelegatingServletInputStream(InputStream sourceStream) {
		if (sourceStream == null) {
			throw new RuntimeException("Source InputStream must not be null");
		}

		this.sourceStream = sourceStream;
	}

	/**
	 * Return the underlying source stream (never {@code null}).
	 */
	public final InputStream getSourceStream() {
		return this.sourceStream;
	}

	@Override
	public int read() throws IOException {
		int data = this.sourceStream.read();
		if (data == -1) {
			this.finished = true;
		}
		return data;
	}

	@Override
	public void close() throws IOException {
		super.close();
		this.sourceStream.close();
	}

	@Override
	public boolean isFinished() {
		return this.finished;
	}

	@Override
	public boolean isReady() {
		return true;
	}

	@Override
	public void setReadListener(ReadListener readListener) {
		throw new UnsupportedOperationException();
	}

}