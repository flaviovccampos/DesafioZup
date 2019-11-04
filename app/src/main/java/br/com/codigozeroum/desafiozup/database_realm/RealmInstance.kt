package br.com.codigozeroum.desafiozup.database_realm

sealed class RealmInstance {
    class InMemory: RealmInstance()
    class Default: RealmInstance()
    class Custom(val value: String): RealmInstance()

    val fileName: String
        get() {
            when (this) {
                is RealmInstance.InMemory -> return "IN_MEMORY"
                is RealmInstance.Custom -> return "CUSTOM_$value"
                else -> return "DEFAULT"
            }
        }

    val schemaVersion: Long
        get() {
            when (this) {
                is RealmInstance.InMemory -> return 1L
                is RealmInstance.Custom -> return 1L
                else -> return 1L
            }
        }
}