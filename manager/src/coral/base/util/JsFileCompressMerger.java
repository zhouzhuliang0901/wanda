/**
 * Project: Coral
 * Source file: JsFileCompressMerger.java
 * Create At 2013-9-12 下午03:03:23
 * Create By 龚云
 */
package coral.base.util;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.Writer;

import org.mozilla.javascript.EvaluatorException;

/**
 * @author 龚云
 * 
 */
public class JsFileCompressMerger implements Closeable {

	public JsFileCompressMerger(File dest, String destCharset, int bufferSize)
			throws IOException, InterruptedException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(dest), destCharset));
		init(bw, true, bufferSize);
	}

	public JsFileCompressMerger(OutputStream dest, String destCharset,
			boolean closeDestStream, int bufferSize) throws IOException,
			InterruptedException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(dest,
				destCharset));
		init(bw, closeDestStream, bufferSize);
	}

	public JsFileCompressMerger(String dest, String destCharset, int bufferSize)
			throws IOException, InterruptedException {
		this(new File(dest), destCharset, bufferSize);
	}

	public JsFileCompressMerger(Writer writer, final boolean closeWriter,
			int bufferSize) throws IOException, InterruptedException {
		init(writer, closeWriter, bufferSize);
	}

	public void close() throws IOException {
		try {
			textFileMerger.close();
		} finally {
			jsFileCompressor.close();
		}
	}

	public JsFileCompressor getJsFileCompressor() {
		return jsFileCompressor;
	}

	public TextFileMerger getTextFileMerger() {
		return textFileMerger;
	}

	public synchronized void mergeAndCompress() throws IOException,
			InterruptedException {
		if (processing)
			throw new RuntimeException(
					"Has been mergeAndCompress, cannot mergeAndCompress again.");
		processing = true;
		InnerMergeThread imt = new InnerMergeThread();
		imt.textFileMerger = textFileMerger;

		InnerCompressThread ict = new InnerCompressThread();
		ict.jsFileCompressor = jsFileCompressor;

		Thread t1 = new Thread(imt);
		t1.start();

		Thread t2 = new Thread(ict);
		t2.start();
		t2.join();

		if (imt.ioException != null)
			throw imt.ioException;
		if (ict.ioException != null)
			throw ict.ioException;
		if (ict.evaluatorException != null)
			throw ict.evaluatorException;
	}

	protected void init(Writer writer, final boolean closeWriter, int bufferSize)
			throws IOException, InterruptedException {
		pr = new PipedReader();
		pw = new PipedWriter();
		pr.connect(pw);
		textFileMerger = new TextFileMerger(pw, true, bufferSize);
		jsFileCompressor = new JsFileCompressor(pr, writer, true);
	}

	private JsFileCompressor jsFileCompressor;

	private PipedReader pr;

	private boolean processing = false;

	private PipedWriter pw;

	private TextFileMerger textFileMerger;

	static class InnerCompressThread implements Runnable {
		@Override
		public void run() {
			try {
				jsFileCompressor.compress();
			} catch (EvaluatorException e) {
				evaluatorException = e;
			} catch (IOException e) {
				ioException = e;
			}
		}

		EvaluatorException evaluatorException = null;
		IOException ioException = null;

		JsFileCompressor jsFileCompressor;
	}

	static class InnerMergeThread implements Runnable {
		@Override
		public void run() {
			try {
				textFileMerger.merge();
			} catch (IOException e) {
				ioException = e;
			} finally {
				try {
					textFileMerger.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		IOException ioException = null;

		TextFileMerger textFileMerger;
	}
}
