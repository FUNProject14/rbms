package org.rootbeer.rbms.logic;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.couchbase.client.CouchbaseClient;

import org.rootbeer.rbms.model.*;

import static org.rootbeer.rbms.util.Database.*;

import org.rootbeer.rbms.util.ModelUtil;
/**
 * ピクチャ関連のクラスです。
 */

public class PictureManagement {
	private PictureManagement(){
	}
	
	/**
	 * @param imagePath 画像のパス
	 * 
	 */
	public static byte[] getImageBytes(String imagePath){
		File imageFile = new File(imagePath);
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(imageFile);
		} catch (IOException e) {
			return null;
		}

		WritableRaster raster = bufferedImage.getRaster();
		DataBufferByte data = (DataBufferByte) raster.getDataBuffer();

		return ( data.getData() );
	}
}
