#import "FloppyPlugin.h"
#if __has_include(<floppy/floppy-Swift.h>)
#import <floppy/floppy-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "floppy-Swift.h"
#endif

@implementation FloppyPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFloppyPlugin registerWithRegistrar:registrar];
}
@end
