
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

import io.github.biezhi.webp.WebpIO;

public class Main {

	public static void main(String[] args) {
		System.out.println("Here");
		String svgFolder = "image_files/";
		File currentDirFile = new File(svgFolder);
		File[] list = currentDirFile.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				// TODO Auto-generated method stub
				return pathname.getName().endsWith(".svg");
			}
			
		});
		
		for(File f: list) {
			convertPngToWebP(convertSvgToPng(svgFolder+f.getName()));
			System.out.println(f.getName());
		}
		// TODO Auto-generated method stub
//		convertSvgToPng();
//		
//		convertPngToWebP();
		
	}

	private static String convertPngToWebP(String fileName) {
		String outputFileName = fileName.replace(".png", ".webp");
		WebpIO.create().toWEBP(fileName, outputFileName);
		return outputFileName;
	}

	private static String convertSvgToPng(String fileName) {
		String pathUri = Paths.get(fileName).toUri().toString();
		String output = fileName.replace(".svg", ".png");
		TranscoderInput tInput = new TranscoderInput(pathUri);
		try {
			OutputStream os = new FileOutputStream(new File(output));
			TranscoderOutput toutput = new TranscoderOutput(os);
			PNGTranscoder myConverter = new PNGTranscoder();
			myConverter.transcode(tInput, toutput);
			os.flush();
			os.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TranscoderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return output;
	}
	
	

}
