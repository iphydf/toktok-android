scalaVersion := "2.11.6"

import android.Keys._

android.Plugin.androidBuild

libraryDependencies ++= Seq(
  "com.android.support" % "gridlayout-v7" % "22.1.1",
  "com.android.support" % "support-v4" % "22.1.1",
  "com.android.support" % "appcompat-v7" % "22.1.1",
  "com.android.support" % "recyclerview-v7" % "22.1.1",
  "com.melnykov" % "floatingactionbutton" % "1.3.0"
)
