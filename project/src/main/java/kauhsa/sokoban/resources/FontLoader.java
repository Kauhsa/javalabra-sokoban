/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kauhsa.sokoban.resources;

import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

/**
 *
 * @author mika
 */
public class FontLoader {
    public static Font loadAwtFontToSlick(String name, int style, int size, GameContainer gc) throws SlickException {
        java.awt.Font awtFont = new java.awt.Font(name, style, size);
        
        if (awtFont == null) {
            return gc.getDefaultFont();
        } else {
            return loadSlickFont(awtFont);
        }
    }
    
    public static UnicodeFont loadSlickFont(java.awt.Font awtFont) throws SlickException {
        UnicodeFont font = new UnicodeFont(awtFont);
        font.addAsciiGlyphs();
        font.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
        font.loadGlyphs();
        return font;
    }
}
