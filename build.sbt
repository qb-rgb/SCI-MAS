name := "SCI-SMA"

version := "1.0"

scalacOptions += "-deprecation"

assemblyJarName in assembly := "wator.jar"

mainClass in assembly := Some("wator.WatorSimulation")
