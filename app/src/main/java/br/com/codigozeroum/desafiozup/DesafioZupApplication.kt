package br.com.codigozeroum.desafiozup

import android.content.Context
import android.util.Log
import androidx.multidex.MultiDexApplication
import io.realm.Realm

class DesafioZupApplication  : MultiDexApplication() {

    init {
        instance = this
    }

    companion object {
        private var instance: DesafioZupApplication? = null

        fun applicationContext(): Context? {
            return instance?.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()

        initializeRealm()
    }

    private fun initializeRealm() {
        Log.e("Application Realm", "Passou AQUI!!")
        Realm.init(this)
    }


}