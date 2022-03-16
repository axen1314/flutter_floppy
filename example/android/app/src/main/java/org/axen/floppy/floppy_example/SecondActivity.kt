package org.axen.floppy.floppy_example

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import org.axen.floppy.core.Floppy

class SecondActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        findViewById<Button>(R.id.btn).setOnClickListener {
            Floppy.instance
                .builder("changeText")
                .arguments(mapOf("text" to "这是修改后的文字"))
                .invoke()
        }
    }
}