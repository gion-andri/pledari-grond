/*******************************************************************************
 * Copyright 2013 Sprachliche Informationsverarbeitung, University of Cologne
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package de.uni_koeln.spinfo.maalr.mongo.util.embedmongo;

import java.util.logging.Logger;

import de.flapdoodle.embedmongo.io.IBlockProcessor;

public class BlockLogWatchProcessor implements IBlockProcessor {

	private static final Logger _logger = Logger.getLogger(BlockLogWatchProcessor.class.getName());

	//private final Reader _reader;
	private final StringBuilder _output = new StringBuilder();
	private final String _success;
	private final String _failure;

	private boolean _initWithSuccess = false;

	private final IBlockProcessor _destination;

	public BlockLogWatchProcessor(String success, String failure, IBlockProcessor destination) {
		_success = success;
		_failure = failure;
		_destination = destination;
	}

	@Override
	public void process(String block) {
		_destination.process(block);
		
		CharSequence line = block;
//		System.out.print(line);
//		System.out.flush();
		_output.append(line);

		if (_output.indexOf(_success) != -1) {
			_initWithSuccess = true;
			gotResult();
		}
		if (_output.indexOf(_failure) != -1) {
			_initWithSuccess = false;
			gotResult();
		}
	}

	@Override
	public void onProcessed() {
		gotResult();
	}

	private synchronized void gotResult() {
		notify();
	}
	
	public synchronized void waitForResult(long timeout) {
		try {
			wait(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isInitWithSuccess() {
		return _initWithSuccess;
	}

	public String getOutput() {
		return _output.toString();
	}

//	public static LogWatch watch(Reader reader, String success, String failed, long timeout) {
//		LogWatch logWatch = new LogWatch(reader, success, failed);
//		logWatch.start();
//
//		synchronized (logWatch) {
//			try {
//				logWatch.wait(timeout);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		return logWatch;
//	}

}
