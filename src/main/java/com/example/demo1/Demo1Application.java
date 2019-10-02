package com.example.demo1;

import com.sun.jna.Native;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.W32APIOptions;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo1Application extends Application {

    public static void main(String[] args) {
       SpringApplication.run(Demo1Application.class, args);
        launch(args);
            //нативный вызов программы калькулятор на передний план
     /*   WinDef.HWND tst= MyUser32.instance.FindWindow(null, "Калькулятор");
        MyUser32.instance.SetWindowPos(tst, -1, 200, 200, 100, 100, 0x0040|0x0002|0x0001);
        MyUser32.instance.EnableWindow(tst, true);
*/

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/formMy.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

/*

        WinDef.HWND hWnd = User32.INSTANCE.FindWindow("className", "windowName");
        User32.INSTANCE.ShowWindow(hWnd, WinUser.SW_MINIMIZE);
*/



    }

    public interface MyUser32  extends User32 {
        static final MyUser32 instance=(MyUser32) Native.loadLibrary("user32", MyUser32.class, W32APIOptions.DEFAULT_OPTIONS);
        public boolean SetWindowPos(HWND hwnd, int hwnd2, int arg1, int arg2, int arg3, int arg4, int flags);
        public int EnableWindow(HWND hwnd, boolean enabled);
    }

}
