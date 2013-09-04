package net.srcz.android.screencast.api.injector;

import java.awt.event.KeyEvent;

import com.android.chimpchat.core.PhysicalButton;

public class KeyCodeConverter {

	public static String getKeyCode(KeyEvent e) {
		char c = e.getKeyChar();

		String code = null;
		
		if(Character.isLetter(c))
			code = Character.toLowerCase(c) + "";
		if(Character.isDigit(c))
			code = c + "";
		
		if(c == '\n')
			code = PhysicalButton.ENTER.getKeyName();

		if(c == ' ')
			code = " "; 
 
		if(c == '/')
			code = "/"; 

		if(c == '\\')
			code = "\\"; 

		if(c == ',')
			code = ","; 

		if(c == ';')
			code = ";"; 

		if(c == '.')
			code = "."; 

		if(c == '*')
			code = "*"; 

		if(c == '+')
			code = "+"; 

		if(c == '-')
			code = "-"; 

		if(c == '=')
			code = "="; 

		if(e.getKeyCode() == KeyEvent.VK_HOME)
			code = PhysicalButton.HOME.getKeyName(); 

		if(e.getKeyCode() == KeyEvent.VK_PAGE_UP)
			code = PhysicalButton.MENU.getKeyName(); 

		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
			code = PhysicalButton.BACK.getKeyName(); 

		if(e.getKeyCode() == KeyEvent.VK_F3)
			code = PhysicalButton.SEARCH.getKeyName(); 

		if(e.getKeyCode() == KeyEvent.VK_F5)
			code = PhysicalButton.SEARCH.getKeyName(); 

		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			code = PhysicalButton.DPAD_RIGHT.getKeyName(); 

		if(e.getKeyCode() == KeyEvent.VK_UP)
			code = PhysicalButton.DPAD_UP.getKeyName(); 

		if(e.getKeyCode() == KeyEvent.VK_DOWN)
			code = PhysicalButton.DPAD_DOWN.getKeyName(); 

		if(e.getKeyCode() == KeyEvent.VK_SHIFT)
			code = PhysicalButton.DPAD_CENTER.getKeyName(); 

		return code;
	}
	
}
