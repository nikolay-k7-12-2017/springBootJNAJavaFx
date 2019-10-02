package testirov;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.Advapi32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.W32APIOptions;


public class Main {

    // пример доступа к окну приложения Калькулятор
    public interface MyUser32  extends User32 {
         MyUser32 instance= Native.load("user32", MyUser32.class, W32APIOptions.DEFAULT_OPTIONS);
        // MyUser32 Native.Library
         boolean SetWindowPos(HWND hwnd, int hwnd2, int arg1, int arg2, int arg3, int arg4, int flags);
         int EnableWindow(HWND hwnd, boolean enabled);

        @Override
        void PostMessage(HWND hWnd, int msg, WPARAM wParam, LPARAM lParam);
    }
    public interface MoreAdvapi32 extends Advapi32{
        MoreAdvapi32 instance= Native.load("advapi32",MoreAdvapi32.class,W32APIOptions.DEFAULT_OPTIONS);
        boolean SetWindowPos(WinDef.HWND hwnd, int hwnd2, int arg1, int arg2, int arg3, int arg4, int flags);
        int EnableWindow(WinDef.HWND hwnd, boolean enabled);
    }
    public static void main(String[] params){
       WinDef.HWND tst= MyUser32.instance.FindWindow(null, "Калькулятор");
        MyUser32.instance.SetWindowPos(tst, -1, 200, 200, 100, 100, 0x0040|0x0002|0x0001);
        MyUser32.instance.EnableWindow(tst, true);



    }
}
