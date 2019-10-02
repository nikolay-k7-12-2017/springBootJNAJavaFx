package testirov;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.W32APIOptions;


public class Main2 {
    private static final WinDef.HWND HWND = User32Ext.USER32EXT.FindWindow("AIMP2_RemoteInfo", null);
    private static final int WM_USER = 0x0400;
    private static final int WM_AIMP_COMMAND = WM_USER + 0x75;

    public static void main(String[] args) {
        sendMsgToAimp(17);
    }

    public interface User32Ext extends User32 {

        User32Ext USER32EXT = Native.load("user32", User32Ext.class, W32APIOptions.DEFAULT_OPTIONS);

        int SendMessage(HWND hWnd, int Msg, int wParam, int lParam);
    }

    private static WinDef.HWND getHandler() {
        return HWND == null ? User32Ext.USER32EXT.FindWindow("AIMP2_RemoteInfo", null) : HWND;
    }

    private static void sendMsgToAimp(int cmdId) {
        User32Ext.USER32EXT.SendMessage(getHandler(), WM_AIMP_COMMAND, cmdId, 0);
    }




}
