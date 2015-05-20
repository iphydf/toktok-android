package im.tox.toktok.app

import java.util.Collections

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.{ActionBarActivity, ActionBarDrawerToggle}
import android.support.v7.view.ActionMode
import android.support.v7.widget.{LinearLayoutManager, RecyclerView, Toolbar}
import android.view.{Menu, MenuItem, View}
import android.widget.{AdapterView, ArrayAdapter, ListView}
import com.melnykov.fab.FloatingActionButton
import im.tox.toktok.R

class Main extends ActionBarActivity with RecyclerBase.ViewHolder.ClickListener {

  private var mainToolbar: Toolbar = null
  private var adapter: RecyclerBase = null
  private val actionModeCallback = new ActionModeCallback
  private var actionMode: ActionMode = null
  private val mPlanetTitles = Array("Menu Item 1", "Menu Item 2")
  private var mDrawerLayout: DrawerLayout = null
  private var mDrawerList: ListView = null
  private var mDrawerToggle: ActionBarDrawerToggle = null

  protected override def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    mainToolbar = findViewById(R.id.toolbar).asInstanceOf[Toolbar]
    setSupportActionBar(mainToolbar)
    setTitle("Inbox")
    adapter = new RecyclerBase(this)
    val recyclerView = findViewById(R.id.mainRecyclerView).asInstanceOf[RecyclerView]
    val layoutManager = new LinearLayoutManager(this)
    recyclerView.setLayoutManager(layoutManager)
    recyclerView.setAdapter(adapter)
    mDrawerLayout = findViewById(R.id.drawer_layout).asInstanceOf[DrawerLayout]
    mDrawerList = findViewById(R.id.left_drawer).asInstanceOf[ListView]
    mDrawerList.setAdapter(new ArrayAdapter[String](this, R.layout.drawer_item, mPlanetTitles))
    mDrawerList.setOnItemClickListener(new DrawerItemClickListener)
    mDrawerLayout.setScrimColor(getResources.getColor(android.R.color.transparent))
    mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mainToolbar, R.string.app_name, R.string.app_name) {
      override def onDrawerClosed(view: View): Unit = {
        super.onDrawerClosed(view)
        invalidateOptionsMenu()
      }

      override def onDrawerOpened(drawerView: View): Unit = {
        super.onDrawerOpened(drawerView)
        invalidateOptionsMenu()
      }
    }
    mDrawerLayout.setDrawerListener(mDrawerToggle)
    getSupportActionBar.setHomeButtonEnabled(true)
    mDrawerToggle.syncState()
    val fab = findViewById(R.id.fab).asInstanceOf[FloatingActionButton]
    fab.attachToRecyclerView(recyclerView)
  }

  override def onCreateOptionsMenu(menu: Menu): Boolean = {
    getMenuInflater.inflate(R.menu.menu_main, menu)
    super.onCreateOptionsMenu(menu)
  }

  override def onOptionsItemSelected(item: MenuItem): Boolean = {
    item.getItemId match {
      case android.R.id.home =>
        true
      case _ =>
        super.onOptionsItemSelected(item)
    }
  }

  def onItemClicked(position: Int): Unit = {
    if (actionMode != null) {
      toggleSelection(position)
    } else {
      val myIntent = new Intent(this, classOf[SecondActivity])
      myIntent.putExtra("title", adapter.getItem(position))
      this.startActivity(myIntent)
    }
  }

  def onItemLongClicked(position: Int): Boolean = {
    if (actionMode == null) {
      actionMode = startSupportActionMode(actionModeCallback)
    }
    toggleSelection(position)
    true
  }

  private def toggleSelection(position: Int): Unit = {
    adapter.toogleSelection(position)
    val count = adapter.getSelectedItemCount
    if (count == 0) {
      actionMode.finish()
    } else {
      actionMode.setTitle(count + " Selected")
      actionMode.invalidate()
    }
  }

  private class DrawerItemClickListener extends AdapterView.OnItemClickListener {
    override def onItemClick(parent: AdapterView[_], view: View, position: Int, id: Long): Unit = {
    }
  }

  private class ActionModeCallback extends ActionMode.Callback {
    @SuppressWarnings(Array("unused")) def onCreateActionMode(mode: ActionMode, menu: Menu): Boolean = {
      mode.getMenuInflater.inflate(R.menu.menu_mail_selected, menu)
      true
    }

    def onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean = {
      false
    }

    def onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean = {
      val a = adapter.getSelectedItems
      Collections.reverse(a)
      item.getItemId match {
        case R.id.action_delete =>
          import scala.collection.JavaConversions._
          a.foreach(adapter.removeItems(_))
          mode.finish()
          true
        case _ =>
          false
      }
    }

    def onDestroyActionMode(mode: ActionMode): Unit = {
      adapter.clearSelection()
      actionMode = null
    }
  }

}
