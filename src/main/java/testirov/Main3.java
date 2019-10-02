package testirov;


import org.bridj.Platform;
import org.bridj.Pointer;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.shell.ITaskbarList3;
import org.bridj.jawt.JAWTUtils;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent; import java.awt.event.ActionListener;




public class Main3 extends JFrame implements ActionListener, ChangeListener {
    private ITaskbarList3 list;
    private JSlider slider;
    private Pointer<?> hwnd;

    private Main3() throws ClassNotFoundException {
        super("TaskbarList Demo (" + (Platform.is64Bits() ? "64 bits" : "32 bits") + ")");
        list = COMRuntime.newInstance(ITaskbarList3.class);
        getContentPane().add("Center", new JLabel("Hello Native Windows 7 World !"));
        Box box = Box.createVerticalBox();
        int min = 0;
        int max = 300;
        int val = (min + max / 2);
        slider = new JSlider(min, max, val);
        slider.addChangeListener(this);
        box.add(slider);
        ButtonGroup group = new ButtonGroup();
        for (ITaskbarList3.TbpFlag state : ITaskbarList3.TbpFlag.values()) {
            JRadioButton cb = new JRadioButton(state.name());
            group.add(cb);
            cb.putClientProperty(ITaskbarList3.TbpFlag.class, state);
            cb.setSelected(state == ITaskbarList3.TbpFlag.TBPF_NORMAL);
            cb.addActionListener(this);
            box.add(cb);
        }
        getContentPane().add("South", box);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        list.Release();
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
        long hwndVal = JAWTUtils.getNativePeerHandle(this);
        hwnd = Pointer.pointerToAddress(hwndVal);
        list.SetProgressValue((Pointer) hwnd, slider.getValue(), slider.getMaximum());
    }

    @Override
    public void stateChanged(ChangeEvent actionEvent) {
        list.SetProgressValue((Pointer) hwnd, slider.getValue(), slider.getMaximum());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JRadioButton button = ((JRadioButton) actionEvent.getSource());
        if (button.isSelected()) {
            ITaskbarList3.TbpFlag flag = (ITaskbarList3.TbpFlag) button.getClientProperty(ITaskbarList3.TbpFlag.class);
            list.SetProgressValue((Pointer) hwnd, slider.getValue(), slider.getMaximum());
            list.SetProgressState((Pointer) hwnd, flag);
        }
    }

    public static void main(String[] arguments) throws Exception {
        Main3 f = new Main3();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }
}


