# INFO707-TP
## Gestion de parking train.
On veut réaliser un système de gestion de zones de stationnement dans une gare ferroviaire. Tout
stationnement se fait dans une voie de garage qui ne peut accueillir qu'un seul train. Les voies de
garage sont regroupées dans une plateforme. Cette dernière contient un nombre n de voies. L'accès
à la plateforme s'effectue, en entrée comme en sortie, par un nœud ferroviaire qui est un tronçon
de voie ferrée qui ne peut être empruntée que par un seul train à la fois, ce qui implique un certain
temps de transit (que l'on ne vous demande pas de modéliser explicitement mais dont il faut tenir
compte au niveau des synchronisations). L'accès au nœud est géré par un opérateur qui, en fonction
des demandes émises par les trains, doit traiter les cas de figure suivants :
- Il n'y a aucune demande en entrée ou en sortie : l'opérateur n'a rien à faire.
- Il y a au moins une demande en sortie et pas de demande en entrée : l'opérateur alloue le
nœud à un des trains voulant sortir.
- Il y a au moins une demande en entrée et pas de demande en sortie : l'opérateur alloue le
nœud à un des trains voulant entrer sauf s'il n'y a aucune voie disponible. Dans ce dernier
cas, l'accès en entrée est provisoirement bloqué jusqu'à ce qu'un train sorte.
- Il y a au moins une demande en entrée et au moins une demande en sortie : l'opérateur
donne la priorité à un des trains voulant entrer afin de soulager le réseau général. Toutefois,
si l'accès en entrée est bloqué faute de voie disponible, la priorité est au contraire donnée à
un train qui veut sortir.
