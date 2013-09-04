package net.srcz.android.screencast.api.injector;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import com.android.chimpchat.ChimpChat;
import com.android.chimpchat.adb.AdbChimpDevice;
import com.android.chimpchat.core.TouchPressType;
import com.android.ddmlib.IDevice;

public class Injector {
	
	AdbChimpDevice chimpDevice;
	IDevice device;

	ChimpChat chimpChat;
	
	public static Socket s;
	OutputStream os;
	Thread t = new Thread("Agent Init") {
		public void run() {
			try {
				
				chimpDevice = (AdbChimpDevice) chimpChat.waitForConnection(1000, device.getSerialNumber());
								
				screencapture.start();
				
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	};

	public ScreenCaptureThread screencapture;

	public Injector(ChimpChat chimpChat, IDevice d) throws IOException {
		this.chimpChat = chimpChat;
		this.device = d;
		this.screencapture = new ScreenCaptureThread(d);
	}

	public void start() {
		t.start();
	}

	public void close() {
		try {
			if (os != null) {
				os.write("quit\n".getBytes());
				os.flush();
				os.close();
			}
			s.close();
		} catch (Exception ex) {
			// ignored
		}
		screencapture.interrupt();
		try {
			s.close();
		} catch (Exception ex) {
			// ignored
		}
		try {
			synchronized (device) {
				/*
				 * if(device != null) device.removeForward(PORT, PORT);
				 */
			}
		} catch (Exception ex) {
			// ignored
		}
	}
	
	public void injectMouse(int action, float x, float y) throws IOException {
				
		if (chimpDevice == null) return;

		if ( action == ConstEvtMotion.ACTION_DOWN) {
			chimpDevice.getManager().touchDown((int) x, (int) y); 
		} else if ( action == ConstEvtMotion.ACTION_MOVE) {
			chimpDevice.getManager().touchMove((int) x, (int) y);
		} else if (action == ConstEvtMotion.ACTION_UP) {
			chimpDevice.getManager().touchUp((int) x, (int) y);
		}
	}

	public void injectTrackball(float amount) throws IOException {
		System.out.println("Trackball not implemented");
	}

	public void injectKeycode(int type, String keyCode) {
				
		if (chimpDevice == null) return;
		
		if ( keyCode == null) return;
		
		if ( type == ConstEvtKey.ACTION_DOWN) {
					
			chimpDevice.press(keyCode, TouchPressType.DOWN);
			
		} else if ( type == ConstEvtKey.ACTION_UP) {
			
			chimpDevice.press(keyCode, TouchPressType.UP);
			
		}
	}
}
