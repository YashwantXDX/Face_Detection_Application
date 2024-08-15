package com.facecaptureanddetect;

import org.opencv.core.Core;

public class FaceCaptureAndDetect {
	
	public static void main(String[] args) {
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		CameraInput camImput = new CameraInput();
		camImput.startApp();
		
	}
	
}
