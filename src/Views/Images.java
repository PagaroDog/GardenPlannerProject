package Views;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.image.Image;

/**
 * This class stores all the images of the plants
 * 
 * @author Tommy White
 *
 */
public class Images {
	HashMap<String, ImageWithSourceInfo[]> plantImages;
	/**
	 * The constructor reads the directory containing the images and source info and
	 * creates an array of ImageWithSourceInfo objects for each plant and stores
	 * them in the HashMap plantImages
	 * 
	 * @param directory The path of the directory holding the images
	 */
	public Images(String directory) {
		plantImages = new HashMap<String, ImageWithSourceInfo[]>();
		ArrayList<ImageWithSourceInfo> arrList = new ArrayList<ImageWithSourceInfo>();
		String[] source = new String[2];
		File[] files = new File(directory).listFiles();
		
		ImageWithSourceInfo[] imgArr = new ImageWithSourceInfo[2];
		
		
		for (File file : files) {
		
			arrList.clear();
			if (file.isDirectory()) {
				
				String plant = file.getName();
			
				FilenameFilter pic = (File dir, String name) -> !(name.endsWith(".txt"));
				
				FilenameFilter txt = (File dir, String name) -> name.endsWith(".txt");
				for (int i = 0; i < file.listFiles().length && i < 5; i++) {
					try {

						File currFolder = new File(directory + plant + "/" + i);

						String curr = currFolder.listFiles(pic)[0].getPath();
						
						
						Image img = new Image(new FileInputStream(curr));
						BufferedReader br = new BufferedReader(new FileReader(currFolder.listFiles(txt)[0]));
						br.readLine(); // Ignore empty line
						source[0] = br.readLine(); // Author
						source[1] = br.readLine(); // Link
						br.close();
						
						arrList.add(new ImageWithSourceInfo(img, source.clone()));
					} catch (FileNotFoundException e) {
						File currFolder = new File(directory + plant + '/' + i);
						System.out.println("File Not Found Exception: " + currFolder);
					} catch(IOException e) {
						File currFolder = new File(directory + plant + '/' + i);
						System.out.println("IO Exception: " + currFolder);
					} catch(NullPointerException e) {
						File currFolder = new File(directory + plant + '/' + i);
						System.out.println("Null Pointer Exception: " + currFolder);
					}
				}
				plantImages.put(plant.replace("_", " "), arrList.toArray(imgArr).clone());
			}
		}
	}

	public HashMap<String, ImageWithSourceInfo[]> getPlantImages() {
		return plantImages;
	}

	public void setPlantImages(HashMap<String, ImageWithSourceInfo[]> plantImages) {
		this.plantImages = plantImages;
	}
}
