Quentin Baert  
Master MOCAD

# SCI - Travaux sur les systèmes multi-agents

## Contenu du projet

Le projet regroupe les trois travaux suivants :
- Simulation d'une chambre à particules
- Simulation proies/prédateurs
- Simulation de chasse

## Récupération

Le projet se trouve sur Github à l'adresse suivante : `https://github.com/qw0te/https://github.com/qw0te/SCI-SMA.git`.

Pour le récupérer, exécuter la commande suivante :
```
$ git clone https://github.com/qw0te/SCI-SMA.git
```

## Architecture

Les sources du projet respectent l'architecture suivante :

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
│       ├── hunt
│       │   ├── HuntEnvironment.scala
│       │   ├── HuntMAS.scala
│       │   ├── HuntSimulation.scala
│       │   ├── HuntUI.scala
│       │   ├── Hunter.scala
│       │   ├── ModelBuilder.scala
│       │   ├── Prey.scala
│       │   ├── Wall.scala
│       │   └── hunt.scala
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

- Le package `core` contient toutes les classes abstraites correspondant à une simulation multi-agents.  
- Le package `particles` contient toutes les classes correspondant à une simulation de chambre à particles.  
- Le package `wator` contient toutes les classes correspondant à une simulation proie-prédateur.
- Le package `hunt` contient toutes les classes correspondant à une simulation de chasse.

## Informations complémentaires

- Afin de faciliter l'observation des différents résultats, le projet est fournis avec plusieurs scripts dont l'utilitée sera décrite par la suite. Ces scripts se trouvent dans le dossier `scripts` situé à la racine du projet. Il est important de lancer les scripts depuis la racine du projet : `scripts/[nom du script]`.

- Le projet est codé en Scala avec l'aide de l'outil SBT (`http://www.scala-sbt.org/download.html`). SBT est nécessaire à l'utilisation des scripts fournis avec le projet.

- Le script `cleanJars.sh` permet de supprimer les `jar` exécutables crées à la compilation :
```
scripts/cleanJars.sh
```

## Simulation de chambre à particules

L'objectif est ici de simuler une chambre à particules. C'est à dire un environnement fermé dans lequel des particules circulent selon une direction qui leur est propre et qui s'entrechoquent, entre elles ou contre les paroies de la chambre.

### Compilation

Pour compiler, lancer le script `compile.sh` avec le paramètre `particles`. Un jar exécutable `particles.jar` est alors généré à la racine du projet.

```
$ scripts/compile.sh particles
```

### Exécution

Pour exécuter le `jar` entrer la commande suivante :

```
java -jar particleRoom.jar -width [x] -height [y] -number [n] -size [s] -sleep [z]
```

Avec :
- x : largeur de la chambre à particules
- y : hauteur de la chambre à particules
- n : nombre de particules à mettre dans la chambre
- s : taille graphique des particules
- z : temps (en ms) à attendre entre deux tours de la simulation

L'ordre des paramètres n'importe pas.

### Commentaires

- Chaque particule possède des attributs qui indiquent sa position actuelle ainsi que sa direction suivant l'axe horizontal et l'axe vertical. Lorsque deux particules s'entrechoquent, elles échangent leurs directions. Lorsqu'une particule rebondit contre une paroie de la chambre, sa direction est inversée selon les deux axes (horizontal et vertical).  
Cela permet d'avoir des particules qui prennent les huits directions possibles.

- Lors de l'initialisation de la chambre, les particules y sont placées aléatoirement. Il se peut qu'une particule soit ajoutée à l'environnement avec une direction horizontale et verticale nulle. La particule est alors immobile, jusqu'à ce qu'elle entre en collision avec une autre particule.

- Afin de pouvoir suivre les déplacement des particules plus facilement, chacune d'elle se voit attribuer une couleur aléatoire lors de son ajout dans l'environnement.

## Simulation proies-prédateurs

Cette partie du projet permet de simuler un environnement dans lequel évolue une proie (le thon) ainsi que son prédateur (le requin). On considère que le thon ne peut mourir qu'en étant mangé par le requin. Le requin quand à lui peut mourrir de faim s'il ne mange pas de thon pendant un certain laps de temps. Chacunes des deux espèces possède également une période de reproduction qui indique une période après laquelle le poisson peut en déposer un autre, du même type que lui, dans une case adjacente.

### Compilation

Pour compiler, lancer le script `compile.sh` avec le paramètre `wator`. Un jar exécutable `wator.jar` est alors généré à la racine du projet.

```
$ scripts/compile.sh wator
```

### Exécution

Pour exécuter le `jar` entrer la commande suivante :
```
java -jar wator.jar -width [x] -height [y] -nTuna [nt] -btTuna [bt] -nShark [ns] -btShark [bs] -stShart [st] -size [s] -sleep [z]
```

Avec :
- x : largeur de la simulation
- y : hauteur de la simulation
- nt : nombre de thons à placer dans la simulation
- ns : nombre de requins à placer dans la simulation
- bt : période de reproduction des thons
- bs : période de reproduction des requins
- st : période avant laquelle un requin meurt de faim
- s : taille (graphique) des poissons
- z : temps (en ms) d'attente entre chaque tour de simulation

L'ordre des paramètres n'importe pas.

### Commentaires

- Lors de l'exécution, des données sont collecter pour créer des graphes par la suite.
Pour créer les graphes, exécuter le script `getPlot.sh` depuis la racine du projet et de la manière suivante :
```
$ scripts/getPlot.sh
```
Les graphes seront ensuite disponibles dans le dossier `watordata` accéssible depuis la racine du projet.  
Pour supprimer les données d'une simulation, un script `cleanWatorData.sh` est fourni.
```
$ scripts/cleanWatorData.sh
```

- Un requin mange un thon s'il est dans son voisinnage de Moore.

- Les thons se déplacent aléatoirement dans l'environnement.

- La stratégie de reproduction est la même pour les deux espèces, à savoir : d'abord se déplacer puis se reproduire. Le poisson peut de cette manière se reproduire dans la cellule de l'environnement qu'il vient de quitter.

- Voici des paramètres qui donnent lieu à une simulation qui semble équilibrée :
```
java -jar wator.jar -width 200 -height 200 -nTuna 10000 -btTuna 5 -nShark 5000 -btShark 7 -stShark 5 -size 5 -sleep 0
```
Trouver de tels paramètres s'avère être un exercice difficile. Il s'agit de tatonner jusqu'à apercevoir une situation qui semble viable, puis d'affiner les paramètres à partir de cette situation.  
La situation ci-dessus converge vers le même type de situations : les thons sont toujours en bien plus grand nombre que les requins, la survie des requins tient souvent au fait qu'un ou deux individus arrivent à percer un banc de thons. Une fois cela fait le reste des requins meurent et une nouvelle génération est issus des requins qui sont dans le banc de thons.

## Simulation de chasse

Dans cette dernière partie, la simulation met en place deux types d'agents : les proies et les chasseurs. Les proies se déplacent aléatoirement dans l'environnement alors que les chasseurs utilisent l'algorithme de Dijkstra pour trouver le plus court chemin entre eux et la proie la plus proche. Contrairement au requins de `wator` qui se contentaient de manger un thon s'il était à proximité, on a ici des chasseurs qui mettent en place une stratégie pour atteindre leur cible.  
Il existe également des agents murs qui sont simplement des éléments de l'environnement mais qui ne prennent aucune décision lors de la simulation. Ils sont simplement là pour rendre des cellules de l'environnement inaccessibles.  
La simulation s'arrête lorsque toutes les proies ont été mangées.

### Compilation

Pour compiler, lancer le script `compile.sh` avec le paramètre `hunt`. Un jar exécutable `hunt.jar` est alors généré à la racine du projet.

```
$ scripts/compile.sh hunt
```

### Exécution

Pour exécuter le `jar` entrer la commande suivante :
```
java -jar hunt.jar -model [m] -size [s] -sleep [z]
```

Avec :
- m : chemin vers un fichier texte model
- s : taille (graphique) des agents
- z : temps (en ms) d'attente entre chaque tour de simulation

L'ordre des paramètres n'importe pas.

### Commentaires

- Un système de modèle sous forme de fichier texte a été implémenté. Il permet, à partir d'un fichier texte, de générer l'environnement correspondant. Un environnement est décrit de la manière suivante :
  - un '#' correspond à un mur
  - un 'P' correspond à une proie
  - un 'H' correspond à un chasseur
  - tout autre caractère est considéré comme une cellule vide


- Pour qu'un fichier soit recevable, il faut bien s'assurer que toutes les lignes du fichier aient bien la même taille, les environnement ne pouvant être que des carrés/rectangles.  
Un exemple d'environnement qui contient une proie et un chasseur pourrait donc être :
```
###################
#................P#
#.................#
#####.........#####
#.................#
#H................#
###################
```
