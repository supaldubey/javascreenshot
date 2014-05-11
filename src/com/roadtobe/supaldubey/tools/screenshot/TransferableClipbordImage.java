/**
 * 
 */
package com.roadtobe.supaldubey.tools.screenshot;

import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * @author Supal Dubey
 *
 */
class TransferableClipbordImage implements Transferable {  
    
    private Image image;  
      
    public TransferableClipbordImage( Image image ) {  
        this.image = image;  
    }  
      
    @Override
    public Object getTransferData( DataFlavor flavor ) throws UnsupportedFlavorException, IOException {  
        if ( flavor.equals( DataFlavor.imageFlavor ) && image != null ) {  
            return image;  
        }  
        else {  
            throw new UnsupportedFlavorException( flavor );  
        }  
    }  
      
    @Override
    public DataFlavor[] getTransferDataFlavors() {  
        return new DataFlavor[]{DataFlavor.imageFlavor};  
    }  
      
    @Override
    public boolean isDataFlavorSupported( DataFlavor flavor ) {  
        DataFlavor[] flavors = getTransferDataFlavors();  
        for (DataFlavor dataFlavor: flavors) {  
            if ( flavor.equals( dataFlavor ) ) {  
                return true;  
            }  
        }  
          
        return false;  
    }  
} 