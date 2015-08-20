package im.tox.toktok.app.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import im.tox.toktok.R

class MainTokTok extends AppCompatActivity{

  override protected def onCreate(savedInstanceState: Bundle): Unit ={
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_toktok)
  }

  override protected def onStart(): Unit ={
    super.onStart()
  }

}