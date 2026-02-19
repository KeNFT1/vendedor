# Moshi
-keepclassmembers class * {
    @com.squareup.moshi.Json <fields>;
}
-keep class com.vendedor.app.**.model.** { *; }

# Retrofit
-keepattributes Signature
-keepattributes *Annotation*

# Room
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
