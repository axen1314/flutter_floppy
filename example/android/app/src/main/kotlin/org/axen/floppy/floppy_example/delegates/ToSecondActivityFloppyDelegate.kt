package org.axen.floppy.floppy_example.delegates

import android.content.Context
import android.content.Intent
import org.axen.floppy.annotation.FloppyProvider
import org.axen.floppy.core.FloppyHandler
import org.axen.floppy.floppy_example.SecondActivity

@FloppyProvider("toSecondActivity")
fun navigateToSecondActivity(
    context: Context?,
    method: String,
    arguments: Any?,
    handler: FloppyHandler?
) {
    context?.let {
        it.startActivity(Intent(it, SecondActivity::class.java))
    }
}