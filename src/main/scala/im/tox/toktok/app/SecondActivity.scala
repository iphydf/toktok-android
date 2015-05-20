package im.tox.toktok.app

import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.app.ActionBarActivity
import android.support.v7.widget.Toolbar
import android.view.{Menu, MenuItem, View}
import im.tox.toktok.R

class SecondActivity extends ActionBarActivity {
  private var mainToolbar: Toolbar = null

  protected override def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_second)
    mainToolbar = findViewById(R.id.secondtoolbar).asInstanceOf[Toolbar]
    setSupportActionBar(mainToolbar)
    getSupportActionBar.setHomeButtonEnabled(true)
    mainToolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha)
    mainToolbar.setNavigationOnClickListener(new View.OnClickListener() {
      def onClick(v: View): Unit = {
        NavUtils.navigateUpFromSameTask(SecondActivity.this)
      }
    })
    val intent = getIntent
    setTitle(intent.getStringExtra("title"))
  }

  override def onCreateOptionsMenu(menu: Menu): Boolean = {
    getMenuInflater.inflate(R.menu.menu_second, menu)
    true
  }

  override def onOptionsItemSelected(item: MenuItem): Boolean = {
    val id = item.getItemId
    if (id == R.id.action_settings) {
      true
    } else {
      super.onOptionsItemSelected(item)
    }
  }
}
