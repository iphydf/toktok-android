// General settings
organization  := "im.tox"
name          := "toktok"
scalaVersion  := "2.11.6"

// Dependencies.
libraryDependencies ++= Seq(
  "com.android.support" % "gridlayout-v7" % "22.1.1",
  "com.android.support" % "support-v4" % "22.1.1",
  "com.android.support" % "appcompat-v7" % "22.1.1",
  "com.android.support" % "recyclerview-v7" % "22.1.1",
  "com.melnykov" % "floatingactionbutton" % "1.3.0",

  organization.value %% "tox4j" % version.value
)
