package im.tox.toktok.app

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import im.tox.toktok.R
import im.tox.toktok.app.models.ApplicationPreferences
import im.tox.toktok.app.utils.ToolbarUtils
import io.realm.Realm

class MainTokTok extends AppCompatActivity {

  private val PREFS_NAME = "TokTokPrefsFile"
  private final val INITIAL_BASE_COLOR = "#B3E5FC"
  private final val INITIAL_TEXT_STATE = 1
  private final val INITIAL_THEME_COLOR = 0

  // UI Variables

  var db: Realm = _
  var ui_color: Int = _
  var ui_dark_mode: Int = _
  var ui_text_dark: Int = _

  // UI Components

  var toolbar : Toolbar = _

  override protected def onCreate(savedInstanceState: Bundle): Unit = {

    // Connection to the application database
    db = Realm.getInstance(this)

    // Loading the app preferences (First app run)
    val appSettings = getSharedPreferences(PREFS_NAME, 0)

    if (appSettings.getBoolean("firstAppRun", true)) {

      db.beginTransaction()

      // Filling the DB with the theme information

      val colorPreference = db.createObject(classOf[ApplicationPreferences])
      colorPreference.setField("ui_color")
      ui_color = Color.parseColor(INITIAL_BASE_COLOR)
      colorPreference.setFieldValue(ui_color)

      val darkText = db.createObject(classOf[ApplicationPreferences])
      darkText.setField("ui_dark_text")
      darkText.setFieldValue(INITIAL_TEXT_STATE)
      ui_text_dark = INITIAL_TEXT_STATE

      val darkTheme = db.createObject(classOf[ApplicationPreferences])
      darkTheme.setField("ui_dark_theme")
      darkTheme.setFieldValue(INITIAL_THEME_COLOR)
      ui_dark_mode = INITIAL_THEME_COLOR

      db.commitTransaction()

      // Registering that the app was launched

      val editSettings = appSettings.edit()
      editSettings.putBoolean("firstAppRun", false)
      editSettings.commit()

      Log.d("TokTok", "FirstTime")

    } else {

      // Loading the theme with the DB information

      val ui_list = db.allObjects(classOf[ApplicationPreferences])
      val i = 0

      for (i <- 0 until ui_list.size()) {

        val setting = ui_list.get(i)

        setting.getField match {
          case "ui_color" => ui_color = setting.getFieldValue
          case "ui_dark_text" => ui_text_dark = setting.getFieldValue
          case "ui_dark_theme" => ui_dark_mode = setting.getFieldValue

        }
      }

      Log.d("TokTok", "other - Time")

    }

    // Loading the ui according with the DB

    if (ui_dark_mode == 1) {
      setTheme(R.style.AppTheme_Dark)
    } else {
      setTheme(R.style.AppTheme)
    }

    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_toktok)

    initToolbar()

  }

  def initToolbar(): Unit ={
    toolbar = findViewById(R.id.app_toolbar).asInstanceOf[Toolbar]
    toolbar.setPadding(0,ToolbarUtils.setToolbarSize(getResources),0,0)

    if(ui_dark_mode == 1){
      toolbar.setBackgroundColor(getResources.getColor(R.color.base_color_dark_toolbar))
      toolbar.setTitleTextColor(getResources.getColor(R.color.white))
    }
    else{

      Log.d("TokTok",ui_color+"")

      toolbar.setBackgroundColor(ui_color)

      if(ui_text_dark == 1){
        toolbar.setTitleTextColor(getResources.getColor(R.color.base_color_dark_toolbar_text))
      }
      else{
        toolbar.setTitleTextColor(getResources.getColor(R.color.white))
      }
    }

    setSupportActionBar(toolbar)

  }

}