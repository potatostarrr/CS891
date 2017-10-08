package edu.vanderbilt.imagecrawler.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import edu.vanderbilt.imagecrawler.platform.PlatformImage;

/**
 * Stores platform-independent meta-data about an Image and also
 * provides methods for common image- and file-related tasks, such as
 * decoding raw byte arrays into an Image and setting/getting transform
 * and file names.
 */
public class Image {
	private PlatformImage mImage;

	/**
	 * The name of the transform that was applied to this result.
	 */
	private String mFilterName;
	/**
	 * Keeps track of whether operations on this Image succeed.
	 */
	private boolean mSucceeded;

	/**
	 * The source url.
	 */
	private URL mSourceUrl;

	/**
	 * Constructs a new Image object used for wrapping
	 * the actual platform image implementation object.
	 *
	 * @param image The platform image object to be wrapped.
	 */
	public Image(PlatformImage image) {
		mImage = image;
	}

	/**
	 * Construct an Image that wraps a PlatformImage {@code image}
	 * which was downloaded from a URL {@code sourceUrl}.
	 */
	public Image(URL sourceUrl, PlatformImage image) {
		// Initialize other data members.
		mSourceUrl = sourceUrl;
		mFilterName = "";
		mSucceeded = true;
		mImage = image;
	}

	/**
	 * Modifies the source URL of this result. Necessary for when the
	 * result is constructed before it is associated with data.
	 */
	public void setSourceURL(URL url) {
		throw new RuntimeException("Not currently supported.");
	}

	/**
	 * Returns the source URL for this image.
	 */
	public URL getSourceUrl() {
		return mSourceUrl;
	}

	/**
	 * Returns the name of the transform applied to this result.
	 */
	public String getFilterName() {
		return mFilterName;
	}

	/**
	 * Returns true if operations on the Image succeeded, else
	 * false.
	 */
	public boolean getSucceeded() {
		return mSucceeded;
	}

	/**
	 * Sets whether operations on the Image succeeded or failed.
	 */
	public void setSucceeded(boolean succeeded) {
		mSucceeded = succeeded;
	}

	/**
	 * Returns the format of the image from the URL in string form.
	 */
	public String getFormatName() {
		URL url = getSourceUrl();
		String format =
				url.getFile().substring
						(url.getFile().lastIndexOf('.') + 1);
		return format.equalsIgnoreCase("jpeg") ? "jpg" : format;
	}

	/**
     * Writes the image bytes to the output stream.
     *
	 * @param outputStream
	 * @throws IOException
	 */
	public void writeImage(OutputStream outputStream) throws IOException {
		mImage.writeImage(outputStream);
	}
}
