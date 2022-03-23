package org.axen.floppy

import android.app.Activity
import android.content.Context
import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import org.axen.floppy.core.*

/** FloppyPlugin */
class FloppyPlugin: FlutterPlugin, MethodCallHandler, ActivityAware {

  private lateinit var channel : MethodChannel
  private var activity : Activity? = null

  private val floppy: Floppy = Floppy.instance

  private val floppyInterceptor: FloppyDelegate = object: FloppyDelegate {
    override fun invoke(
      context: Context?,
      method: String,
      arguments: Any?,
      handler: FloppyHandler?
    ) {
      channel.invokeMethod(method, arguments, object: Result {
        override fun success(result: Any?) {
          handler?.success(result)
        }

        override fun error(
          errorCode: String?,
          errorMessage: String?,
          errorDetails: Any?) {

        }

        override fun notImplemented() {}
      })
    }
  }

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "org.axen.floppy")
    channel.setMethodCallHandler(this)
    floppy.interceptor = floppyInterceptor
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    val method = call.method
    val delegate = floppy.getDelegate(method)
    if (delegate != null) {
      floppy.builder(call.method)
        .context(activity)
        .arguments(call.arguments)
        .callback(object: FloppyCallback {
          override fun onSuccess(value: Any?) {
            if (value !is Unit) result.success(it)
          }
        })
        .invoke()
    } else {
      result.notImplemented()
    }

  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
    floppy.interceptor = null
  }

  override fun onAttachedToActivity(binding: ActivityPluginBinding) {
    activity = binding.activity
  }

  override fun onDetachedFromActivityForConfigChanges() {
    activity = null
  }

  override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
    activity = binding.activity
  }

  override fun onDetachedFromActivity() {
    activity = null
  }
}
