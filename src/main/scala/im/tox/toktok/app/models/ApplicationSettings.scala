package im.tox.toktok.app.models

import io.realm.RealmObject
import io.realm.annotations.{Ignore, PrimaryKey}

class ApplicationSettings extends RealmObject {

  @PrimaryKey
  private var fieldName: String = ""
  private var fieldValue: Int = -1

  @Ignore
  private var sessionId: Int = -1

  def _FieldValue(field: String): Int = {
    fieldValue
  }

  def setFieldValue(fieldValue: Int): Unit = {
    this.fieldValue = fieldValue
  }

  def _FieldName(): String = {
    fieldName
  }

  def setFieldName(fieldName: String): Unit = {
    this.fieldName = fieldName
  }

  def _SessionId(): Int = {
    sessionId
  }

  def setSessionId(dontPersist: Int): Unit = {
    this.sessionId = dontPersist
  }

}