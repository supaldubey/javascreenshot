/**
 * 
 */
package com.roadtobe.supaldubey.tools.screenshot.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;

import com.roadtobe.supaldubey.tools.screenshot.ScreenCapture;

/**
 * @author Supal Dubey
 *
 */
public class CaptureUI extends JFrame{

	private static final long serialVersionUID = -748514217980689534L;
	
	private JFileChooser fileChooser = new JFileChooser();

	private ScreenCapture screenCapture = new ScreenCapture();
	
	public CaptureUI()
	{
		fileChooser.setFileFilter(new JPGFileFilter());
		createVisuals();
		setSize(300, 100);
		setTitle("Capture UI");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//Place at center of Screen
		setLocationRelativeTo(null);
	}

	private void createVisuals() 
	{
		JButton capture = new JButton("Capture Screen");
		JPanel container = new JPanel();
		container.add(capture);
		getContentPane().add(container);
		addListeners(capture);
	}

	private void addListeners(JButton capture) {
		capture.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveFile();
			}
		});
	}

	
	private void saveFile() {
		int option = fileChooser.showSaveDialog(null);
		setExtendedState(ICONIFIED);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			//Ignore
		}
		BufferedImage image = (BufferedImage) screenCapture .capture();
		if(option == JFileChooser.APPROVE_OPTION)
		{
			try 
			{
				File path = fileChooser.getSelectedFile();
				if(path.getName().indexOf(".jpg") == -1)
				{
					path = new File(path.getPath() + ".jpg");
				}
				ImageIO.write(image, "jpg", path);
			} 
			catch (IOException e1) 
			{
				JOptionPane.showMessageDialog(this, "Unable to capture Screen", "Capture UI", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(this, "Image Saved ", "Capture UI", JOptionPane.INFORMATION_MESSAGE);
		}
	
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new CaptureUI().setVisible(true);
			}
		});
	}
	
	private class JPGFileFilter extends FileFilter
	{
		@Override
		public boolean accept(File file) {
			return file.isDirectory() || file.getName().contains(".jpg") || file.getName().contains(".jpeg");
		}

		@Override
		public String getDescription() {
			return "JPEG / JPG Files Only";
		}
	}
}
