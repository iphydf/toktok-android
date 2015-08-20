package im.tox.toktok.app.models

import io.realm.annotations.PrimaryKey
import io.realm.{RealmObject, Realm}

class Friend extends RealmObject {

  private var name: String = ""
  private var alias: String = ""
  private var color: Int = -1
  private var status: Int = -1
  private var messageStatus: String = ""
  private var favorite: Boolean = false
  private var blocked: Boolean = false


  @PrimaryKey
  private var toxID: String = ""


}