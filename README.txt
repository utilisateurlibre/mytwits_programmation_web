_______
Projet:|
--------------------------------------------------------------------------------------------------------------------------------------
Mon projet est une application web qui permet � plusieurs utilisateurs de s'identifier et poster des messages partager avec les autre 
utilisateurs.
_______
Choix: |
--------------------------------------------------------------------------------------------------------------------------------------
Le projet est d�velopp� avec Java EE(pattern DAO). 
Le pattern DAO (Data Access Object) permet de faire le lien entre la couche m�tier et la couche persistante,
ceci afin de centraliser les m�canismes de mapping entre notre syst�me de stockage et nos objets Java. Il permet 
aussi de pr�venir un changement �ventuel de syst�me de stockage de donn�es (de PostgreSQL vers Oracle par exemple).

La couche persistante correspond, en fait, � notre syst�me de stockage et la couche m�tier correspond � nos objets Java,
 mapper sur notre base. Le pattern DAO consiste � ajouter un ensemble d'objets dont le r�le sera d'aller :
*Lire.
*Ecrire.
*Modifier.
*Supprimer.

Une base de donner sous mysql, j'ai utilis� aussi html et css pour les pages web.
________________  
Fonctionnalit�: |
--------------------------------------------------------------------------------------------------------------------------------------
les utilisateurs sont cr�e sur Mysql workbench. l'application web est compos� de deux pages:
 - page login.jsp: permet de s�identifier avec un login est un password pour passe � la page suivante index.jsp
 - page index.jsp: composer d'un textarea pour saisir le message et un bouton pour envoyer le message. Le message est affich� 
 directement sur la m�me page.