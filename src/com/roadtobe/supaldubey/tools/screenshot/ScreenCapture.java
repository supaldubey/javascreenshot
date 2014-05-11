/**
 * 
 */
package com.roadtobe.supaldubey.tools.screenshot;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;

/**
 * @author Supal Dubey
 *
 */
public class ScreenCapture implements ClipboardOwner {
	
	private static Robot robot ;

	static
	{
		try 
		{
			robot = new Robot();
		} 
		catch (AWTException e) 
		{
			throw new RuntimeException("Unable to Initialize", e);
		}
	}
	
	public Image capture() 
	{
			Image capturedImage = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			TransferableClipbordImage image = new TransferableClipbordImage(capturedImage);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(image, this);
			System.out.println("Captured Image");
			return capturedImage ;
	}

	@Override
	public void lostOwnership(Clipboard clipboard, Transferable contents) {
		System.out.println("Lost Ownership");
	}
	
}  

