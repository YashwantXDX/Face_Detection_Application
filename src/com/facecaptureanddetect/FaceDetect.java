package com.facecaptureanddetect;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;

class FaceDetect {
	
	public String nameOfImage;
	
	public FaceDetect(String imageName) {
		this.nameOfImage = imageName;
	}

	public void detectFace() {
		Mat image = Imgcodecs.imread("images/"+ nameOfImage +".jpg");
		detectImageAndSaveIt(image);
	}
	
	private static void detectImageAndSaveIt(Mat image) {
		
		//Objects
		
		MatOfRect faces = new MatOfRect(); // This is to save more than one face
		
		//Convert image to gray scale
		Mat grayFrame = new Mat();
		Imgproc.cvtColor(image, grayFrame, Imgproc.COLOR_BGR2GRAY);
		
		//Improve Contrast of the Photo for better Result
		Imgproc.equalizeHist(grayFrame, grayFrame);
		
		int height = grayFrame.height();
		int absoulutefaceSize = 0;
		
		if(Math.round(height * 0.2f) > 0)
			absoulutefaceSize = Math.round(height * 0.2f);
		
		//Face Detection
		CascadeClassifier faceCascade = new CascadeClassifier();
		
		//Loading the trained data
		faceCascade.load("data/haarcascade_frontalface_alt2.xml");
		faceCascade.detectMultiScale(grayFrame, faces, 1.1, 2, 0|Objdetect.CASCADE_SCALE_IMAGE, new Size(absoulutefaceSize,absoulutefaceSize), new Size());
		
		//Write to File
		Rect[] faceArray = faces.toArray();
		for(Rect face : faceArray)
			Imgproc.rectangle(image, face, new Scalar(0,0,255), 3);
		
		Imgcodecs.imwrite("images/output.jpg",image);
		System.out.println("File Detection Successful - " + faceArray.length);
		
	}

}