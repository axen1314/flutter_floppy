package org.axen.floppy.floppy_example.delegates

import android.content.Context
import android.content.Intent
import org.axen.floppy.annotation.BindFloppyMethod
import org.axen.floppy.core.FloppyDelegate
import org.axen.floppy.core.FloppyHandler
import org.axen.floppy.floppy_example.SecondActivity

@BindFloppyMethod("toSecondActivity")
class ToSecondActivityFloppyDelegate : FloppyDelegate {
    override fun invoke(
        context: Context?,
        method: String,
        arguments: Any?,
        handler: FloppyHandler?
    ) {
        context?.let {
            it.startActivity(Intent(it, SecondActivity::class.java))
        }
    }
}