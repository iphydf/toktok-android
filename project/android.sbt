resolvers += "Tox4j snapshots" at "https://tox4j.github.io/repositories/snapshots/"

// Android plugins.
addSbtPlugin("com.hanhuy.sbt" % "android-protify" % "1.1.13")
addSbtPlugin("com.hanhuy.sbt" % "android-sdk-plugin" % "1.5.11")

// Common tox4j build rules.
addSbtPlugin("im.tox" % "sbt-plugins" % "0.1-SNAPSHOT")
