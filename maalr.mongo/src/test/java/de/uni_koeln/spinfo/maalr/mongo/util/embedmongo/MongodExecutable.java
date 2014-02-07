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

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import de.flapdoodle.embedmongo.Files;


public class MongodExecutable {

	private static final Logger _logger = Logger.getLogger(MongodExecutable.class.getName());
	
	private final MongodConfig _mongodConfig;
	private final File _mongodExecutable;
	private boolean _stopped;

	private final Distribution _distribution;

	public MongodExecutable(Distribution distribution, MongodConfig mongodConfig, File mongodExecutable) {
		_distribution = distribution;
		_mongodConfig = mongodConfig;
		_mongodExecutable = mongodExecutable;
		Runtime.getRuntime().addShutdownHook(new JobKiller());
	}

	public synchronized void cleanup() {
		if (!_stopped) {
			if (_mongodExecutable.exists() &&  !Files.forceDelete(_mongodExecutable))
				_logger.warning("Could not delete mongod executable NOW: " + _mongodExecutable);
//				_logger.warning("Could not delete temp mongod exe: " + _mongodExecutable);
			_stopped = true;
		}
	}

	class JobKiller extends Thread {

		@Override
		public void run() {
			cleanup();
		}
	}

	public File getFile() {
		return _mongodExecutable;
	}

	public MongodProcess start() throws IOException {
		return new MongodProcess(_distribution,_mongodConfig,this);
	}

}
