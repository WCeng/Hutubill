package startup;

import gui.frame.MainFrame;
import gui.panel.MainPanel;
import gui.panel.ReportPanel;
import gui.panel.SpendPanel;
import util.GUIUtil;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class Bootstrap {
    public static void main(String[] args) throws InterruptedException, InvocationTargetException {
        GUIUtil.useLNF();

        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                MainFrame.instance.setVisible(true);
                MainPanel.instance.workingPanel.show(SpendPanel.instance);
            }
        });
    }
}
