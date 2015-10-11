#!/bin/sh

if [ $1 = "all" ]
then
  echo "[particles.jar] Compiling..."
  sbt particles:assembly
  echo "[particles.jar] Done!"
  echo "[wator.jar] Compiling..."
  sbt wator:assembly
  echo "[wator.jar] Done!"
  echo "[hunt.jar] Compiling..."
  sbt hunt:assembly
  echo "[hunt.jar] Done!"
  mv target/scala-2.10/*.jar .
  echo "All the runnable jars are in the project root directory."
else
  echo "[$1.jar] Compiling..."
  sbt $1:assembly
  echo "[$1.jar] Done!"
  mv target/scala-2.10/$1.jar .
  echo "The runnable jar $1.jar is in the project root directory."
fi
