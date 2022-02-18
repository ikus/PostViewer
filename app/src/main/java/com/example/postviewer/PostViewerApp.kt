package com.example.postviewer
import android.app.Application
import com.example.postviewer.data.Prefs
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PostViewerApp:Application(){
    companion object{
        lateinit var prefs:Prefs
    }
    override fun onCreate() {
        super.onCreate()
        prefs = Prefs(applicationContext)
    }
}
