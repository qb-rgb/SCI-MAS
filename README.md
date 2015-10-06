Quentin Baert  
Master MOCAD

# SCI - Simulation d'une chambre à particules  

## Récupération

Le projet se trouve sur Github à l'adresse suivante : `https://github.com/qw0te/https://github.com/qw0te/SCI-SMA.git`.

Pour le récupérer, exécuter la commande suivante :
```
git clone https://github.com/qw0te/SCI-SMA.git
```

## Compilation

Le projet est codé en Scala. Il nécéssite donc l'outil SBT (`http://www.scala-sbt.org/download.html`) pour être compiler.


Pour compiler, se positionner à la racine et lancer :
```
sbt assembly
```

On trouvera le `jar` exécutable en suivant le chemin suivant : `[racine du projet]/target/scala-2.10/wator.jar`.

Un `jar` exécutable fonctionnel est cependant fournis à la racine du projet.

## Exécution

Pour exécuter le `jar` entrer la commande suivante :
```
java -jar wator.jar -width [x] -height [y] -nTuna [nt] -nShark [ns] -bt[Tuna/Shark] [bt] -stShart [st] -size [s] -sleep [z]
```

Où :
- x correspond à la largeur de la simulation
- y correspond à la hauteur de la simulation
- nt correspond au nombre de thons à placer dans la simulation
- ns correspond au nombre de requins à placer dans la simulation
- bt correspond à la période de reproduction de l'espèce
- st correspond à la période avant laquelle un requin meurt de faim
- s correspond à la taille (graphique) des particules
- z correspond au temps (en ms) d'attente entre chaque tour de simulation de la chambre

L'ordre des paramètres n'importe pas.

Lors de l'exécution, des données sont collecter pour créer des graphes par la suite.

Pour créer les graphes, des scripts `gnuplot` sont fournis :
```
gnuplot buildBalance.plot
gnuplot buildPop.plot
```

Pour visualiser les graphes :
```
open *.png
```

Un script de nettoyage est également fournis afin de supprimer les données et graphes du précédant run :
```
./clean.sh
```

## Détails

### Architecture

Le projet respecte l'architecture suivante :

```
src
├── main
│   ├── java
│   ├── resources
│   └── scala
│       ├── core
│       │   ├── Agent.scala
│       │   ├── Environment.scala
│       │   ├── MAS.scala
│       │   ├── View.scala
│       │   └── core.scala
│       ├── particles
│       │   ├── Particle.scala
│       │   ├── ParticlesEnvironment.scala
│       │   ├── ParticlesGenerator.scala
│       │   ├── ParticlesRoom.scala
│       │   ├── ParticlesRoomUI.scala
│       │   └── particles.scala
│       └── wator
│           ├── Fish.scala
│           ├── FishesGenerator.scala
│           ├── Shark.scala
│           ├── Tuna.scala
│           ├── WatorConfig.scala
│           ├── WatorData.scala
│           ├── WatorEnvironment.scala
│           ├── WatorMAS.scala
│           ├── WatorSimulation.scala
│           ├── WatorUI.scala
│           └── wator.scala
└── test
    ├── java
    ├── resources
    └── scala
```

Le package `core` contient toutes les classes abstraites correspondant à une simulation multi-agents.  
Le package `particles` contient toutes les classes correspondant à une simulation de chambre à particles.  
Le package `wator` contient toutes les classes correspondant à une simulation proie-prédateur.

### Choix de conception

- Les requins mange un thon s'il est dans son voisinnage de Moore.
- Les thons se déplacent aléatoirement dans l'environnement.

- L'option thorique de l'environnement n'est pas implémenté.
