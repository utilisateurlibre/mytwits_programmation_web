_______
Projet:|
--------------------------------------------------------------------------------------------------------------------------------------
Mon projet est une application web qui permet à plusieurs utilisateurs de s'identifier et poster des messages partager avec les autre 
utilisateurs.
_______
Choix: |
--------------------------------------------------------------------------------------------------------------------------------------
Le projet est développé avec Java EE(pattern DAO). 
Le pattern DAO (Data Access Object) permet de faire le lien entre la couche métier et la couche persistante,
ceci afin de centraliser les mécanismes de mapping entre notre système de stockage et nos objets Java. Il permet 
aussi de prévenir un changement éventuel de système de stockage de données (de PostgreSQL vers Oracle par exemple).

La couche persistante correspond, en fait, à notre système de stockage et la couche métier correspond à nos objets Java,
 mapper sur notre base. Le pattern DAO consiste à ajouter un ensemble d'objets dont le rôle sera d'aller :
*Lire.
*Ecrire.
*Modifier.
*Supprimer.

Une base de donner sous mysql, j'ai utilisé aussi html et css pour les pages web.
________________  
Fonctionnalité: |
--------------------------------------------------------------------------------------------------------------------------------------
les utilisateurs sont crée sur Mysql workbench. l'application web est composé de deux pages:
 - page login.jsp: permet de s’identifier avec un login est un password pour passe à la page suivante index.jsp
 - page index.jsp: composer d'un textarea pour saisir le message et un bouton pour envoyer le message. Le message est affiché 
 directement sur la même page.