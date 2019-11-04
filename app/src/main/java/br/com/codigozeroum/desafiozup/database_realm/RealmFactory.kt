package br.com.codigozeroum.desafiozup.database_realm

import io.realm.Realm
import io.realm.RealmConfiguration

class RealmFactory {

    companion object {

        fun make(instance: RealmInstance): Realm {
            val config = RealmConfiguration.Builder()
            config.name(instance.fileName)
            config.schemaVersion(instance.schemaVersion)
            config.deleteRealmIfMigrationNeeded()

            if (instance is RealmInstance.InMemory) {
                config.inMemory()
            }

            val realmConfiguration = config.build()
            return Realm.getInstance(realmConfiguration)
        }

    }

}