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

import de.flapdoodle.embedmongo.distribution.BitSize;
import de.flapdoodle.embedmongo.distribution.Platform;

public class Distribution {

	private final Version _version;
	private final Platform _platform;
	private final BitSize _bitsize;

	public Distribution(Version version, Platform platform, BitSize bitsize) {
		_version = version;
		_platform = platform;
		_bitsize = bitsize;
	}

	public Version getVersion() {
		return _version;
	}

	public Platform getPlatform() {
		return _platform;
	}

	public BitSize getBitsize() {
		return _bitsize;
	}
	
	@Override
	public String toString() {
		return ""+_version+":"+_platform+":"+_bitsize;
	}
	
	public static Distribution detectFor(Version version) {
		BitSize bitSize=BitSize.B32;
		String osArch = System.getProperty("os.arch");
		if (osArch.equals("i686_64") || osArch.equals("x86_64") || osArch.equals("amd64")) bitSize=BitSize.B64;
		
		String osName = System.getProperty("os.name");
		Platform platform=null;
		if (osName.equals("Linux")) platform=Platform.Linux;
		if (osName.startsWith("Windows",0)) platform=Platform.Windows;
		if (osName.equals("Mac OS X")) platform=Platform.OS_X;
		
		if (platform==null) throw new IllegalArgumentException("Could not detect Platform: os.name="+osName);
		
		return new Distribution(version, platform, bitSize);
	}
}
