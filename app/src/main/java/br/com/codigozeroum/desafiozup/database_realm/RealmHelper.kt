package br.com.codigozeroum.desafiozup.database_realm

import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmQuery

class RealmHelper(val instance: RealmInstance = RealmInstance.Default()) {

    val connection: Realm
        get() = RealmFactory.make(instance)

    private fun writeOnRealm(realm: Realm, block: () -> Unit) {
        realm.beginTransaction()
        block()
        realm.commitTransaction()
    }

    fun <T : RealmObject> save(item: T) {
        save(listOf(item))
    }

    fun <T : RealmObject> save(items: List<T>) {
        val connection = this.connection
        writeOnRealm(connection) {
            connection.insertOrUpdate(items)
        }
        connection.close()
    }

    fun <T : RealmObject> update(updateObject: () -> Unit) {
        val connection = this.connection
        writeOnRealm(connection) {
            updateObject()
        }
        connection.close()
    }


    inline fun <reified T : RealmObject> query(): RealmQuery<T> {
        return connection.where(T::class.java)
    }

    inline fun <reified T : RealmObject> findAll(fieldName: String? = null, value: String? = null): List<T> {
        if (fieldName != null && value != null) {
            return query<T>().equalTo(fieldName, value).findAll().toList()
        }

        return query<T>().findAll().toList()
    }

    inline fun <reified T : RealmObject> findFirst(fieldName: String? = null, value: String? = null): T? {
        if (fieldName != null && value != null) {
            return query<T>().equalTo(fieldName, value).findFirst()
        }

        return query<T>().findFirst()
    }

    fun <T : RealmObject> delete(item: T) {
        delete(listOf(item))
    }

    fun <T : RealmObject> delete(items: List<T>) {
        val connection = this.connection
        writeOnRealm(connection) {
            items.forEach {
                it.deleteFromRealm()
            }
        }
        connection.close()
    }


    inline fun <reified T : RealmObject> autoIncrement(): Long {
        val id = query<T>().max("id")?.toLong()
        if (id != null) {
            return id + 1
        }
        return 1
    }

}