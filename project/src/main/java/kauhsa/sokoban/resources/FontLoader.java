package kauhsa.sokoban.resources;

import org.newdawn.slick.Font;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

/**
 * Class containing some helper methods for loading fonts to Slick.
 */
public class FontLoader {
    
    /**
     * Get a Slick font from Awt font.
     * 
     * Uses same parameters than java.awt.Font constructor.
     * 
     * @return Slick Font.
     */
    public static Font loadAwtFontToSlick(String name, int style, int size) throws SlickException {
        java.awt.Font awtFont = new java.awt.Font(name, style, size);
        return loadSlickFont(awtFont);        
    }
    
    private static UnicodeFont loadSlickFont(java.awt.Font awtFont) throws SlickException {
        UnicodeFont font = new UnicodeFont(awtFont);
        font.addAsciiGlyphs();
        font.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
        font.loadGlyphs();
        return font;
    }
}
