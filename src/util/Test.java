package util;

import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadPoolExecutor;

public class Test {
    public static void main(String[] args) {

//        Calendar c = Calendar.getInstance();
//        c.setTime(new Date());
//        c.set(Calendar.DATE, 1);
//
//        c.set(Calendar.HOUR_OF_DAY, 0);
//        c.set(Calendar.MINUTE, 0);
//        c.set(Calendar.SECOND, 0);
//        c.set(Calendar.MILLISECOND, 0);
//        System.out.println(c.getTime());
        System.out.println();
        Date d = new Date();
        System.out.println(d);

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);

        c.set(Calendar.DATE, 1);
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.SECOND, -1);

        System.out.println(c.getTime());
    }
}
