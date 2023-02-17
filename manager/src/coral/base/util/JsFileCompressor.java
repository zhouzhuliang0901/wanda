/**
 * Project: Coral
 * Source file: JsCompressUtils.java
 * Create At 2013-9-12 下午02:16:05
 * Create By 龚云
 */
package coral.base.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;

import wfc.service.log.Log;

import com.yahoo.platform.yui.compressor.JavaScriptCompressor;

/**
 * @author 龚云
 * 
 */
public class JsFileCompressor implements Closeable {

	public JsFileCompressor(File srcFile, String srcCharset, File destFile,
			String destCharset, int bufferSize)
			throws UnsupportedEncodingException, FileNotFoundException {
		if (bufferSize <= 0)
			bufferSize = defaultCharBufferSize;
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(srcFile), srcCharset), bufferSize);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(destFile), destCharset), bufferSize);
		init(br, bw, true);
	}

	public JsFileCompressor(Reader reader, Writer writer, boolean closeStream) {
		init(reader, writer, closeStream);
	}

	public JsFileCompressor(String srcFile, String srcCharset, String destFile,
			String destCharset, int bufferSize)
			throws UnsupportedEncodingException, FileNotFoundException {
		if (bufferSize <= 0)
			bufferSize = defaultCharBufferSize;
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(srcFile), srcCharset), bufferSize);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(destFile), destCharset), bufferSize);
		init(br, bw, true);
	}

	public void close() throws IOException {
		if (writer != null && closeStream)
			writer.close();
	}

	public void compress() throws EvaluatorException, IOException {
		try {
			JavaScriptCompressor jsc = new JavaScriptCompressor(reader,
					new ErrorReporter() {
						@Override
						public void error(String message, String sourceName,
								int line, String lineSource, int lineOffset) {
							Log.error("[WARNING] " + message);
						}

						@Override
						public EvaluatorException runtimeError(String message,
								String sourceName, int line, String lineSource,
								int lineOffset) {
							error(message, sourceName, line, lineSource,
									lineOffset);
							return new EvaluatorException(message);
						}

						@Override
						public void warning(String message, String sourceName,
								int line, String lineSource, int lineOffset) {
							Log.error("[WARNING] " + message);
						}
					});
			jsc.compress(writer, linebreakpos, munge, verbose,
					preserveAllSemiColons, disableOptimizations);
		} finally {
			if (writer != null && closeStream)
				writer.close();
		}
	}

	public int getLinebreakpos() {
		return linebreakpos;
	}

	public boolean isDisableOptimizations() {
		return disableOptimizations;
	}

	public boolean isMunge() {
		return munge;
	}

	public boolean isPreserveAllSemiColons() {
		return preserveAllSemiColons;
	}

	public boolean isVerbose() {
		return verbose;
	}

	public JsFileCompressor setDisableOptimizations(boolean disableOptimizations) {
		this.disableOptimizations = disableOptimizations;
		return this;
	}

	public JsFileCompressor setLinebreakpos(int linebreakpos) {
		this.linebreakpos = linebreakpos;
		return this;
	}

	public JsFileCompressor setMunge(boolean munge) {
		this.munge = munge;
		return this;
	}

	public JsFileCompressor setPreserveAllSemiColons(
			boolean preserveAllSemiColons) {
		this.preserveAllSemiColons = preserveAllSemiColons;
		return this;
	}

	public JsFileCompressor setVerbose(boolean verbose) {
		this.verbose = verbose;
		return this;
	}

	protected void init(Reader reader, Writer writer, boolean closeStream) {
		this.reader = new BufferedReader(reader);
		this.writer = new BufferedWriter(writer);
		this.closeStream = closeStream;
	}

	private boolean closeStream = true;

	private boolean disableOptimizations = false;

	private int linebreakpos = -1;

	private boolean munge = false;

	private boolean preserveAllSemiColons = false;

	private Reader reader;

	private boolean verbose = false;

	private Writer writer;

	private static int defaultCharBufferSize = 8192;

}
