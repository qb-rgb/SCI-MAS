val Particles = config("particles") extend(Compile)
val Wator = config("wator") extend(Compile)
val Hunt = config("hunt") extend(Compile)

val root = project.in(file(".")).
  configs(Particles, Wator, Hunt).
  settings(
    name := "SCI-SMA",
    version := "1.0",
    scalacOptions += "-deprecation"
  ).
  settings(inConfig(Particles)(Classpaths.configSettings ++ Defaults.configTasks ++ baseAssemblySettings ++ Seq(
    assemblyJarName in assembly := "particles.jar",
    mainClass in assembly := Some("particles.ParticlesRoom")
  )): _*).
  settings(inConfig(Wator)(Classpaths.configSettings ++ Defaults.configTasks ++ baseAssemblySettings ++ Seq(
    assemblyJarName in assembly := "wator.jar",
    mainClass in assembly := Some("wator.WatorSimulation")
  )): _*).
  settings(inConfig(Hunt)(Classpaths.configSettings ++ Defaults.configTasks ++ baseAssemblySettings ++ Seq(
    assemblyJarName in assembly := "hunt.jar",
    mainClass in assembly := Some("hunt.HuntSimulation")
  )): _*)
