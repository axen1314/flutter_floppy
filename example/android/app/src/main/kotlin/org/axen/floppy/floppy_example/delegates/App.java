package org.axen.floppy.floppy_example.delegates;

import android.app.Application;

import org.axen.floppy.core.Floppy;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        Floppy.Companion
//                .getInstance()
//                .getDelegates()
//                .put("toSecondActivity", new ToSecondActivityFloppyDelegate());
    }
}
