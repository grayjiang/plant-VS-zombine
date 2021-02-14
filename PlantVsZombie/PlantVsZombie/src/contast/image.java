package contast;
import java.awt.Image;
import java.util.HashMap;

import javax.swing.ImageIcon;

/**
 * put some common method together because they are used quite often
 * @author Administrator
 *
 */

public class image {
	private static HashMap<String, Image> imageMap = new HashMap<String, Image>();

	/**
	 * loading images from the file
	 * @param name images' name
	 * @return image
	 */
	public static Image getImage(String name) {
		if (!imageMap.containsKey(name)) {
			ImageIcon icon = new ImageIcon("images/" + name);
			imageMap.put(name, icon.getImage());
		}
		return imageMap.get(name);
	}
}
