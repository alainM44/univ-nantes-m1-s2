//Tache TR périodique
/*Définition de la période de la tache
  déclaration d'une variable indiquant la fin de l'appli
  Code de la tache 
  -Verrouillage des pages en méméoire
  -Rendre la tache périodique  ->rt_tasj_set_periodic
  -boucle 
  -> dernière instruction attente de la période suivante -> rt_task_wait_period()

  Code du main 
  ---> Création dst lancement de la tâche TR
  attente de x secondes
  indication de fin
*/


//Inclusion des librairies
#include<stdio.h> /*printf()*/
#include<stdlib.h> /*NULL*/
#include<native/task.h>/*rt_task...*/ 
#include<sys/mman.h>  /*mlockall()*/
#include <native/timer.h>// car gestion de tâches périodiques
#include <unistd.h>

/* Période de la tâche  = Période du timer*/

#define TIMERTICKS 1000000000  // En nano secondes
// FLAG DE FIN DE LAPPLI
int FIN=0;
static void Code_TachePer(void* arg)
{
  // verrouiller les tâches mémoire
  mlockall(MCL_CURRENT|MCL_FUTURE);
  //Rendre la tâche périodique

  /**
   *
   *\param Null : descriptor de la tâche elle même.
   *\param TM_NOW instant courant (on la rend périodique à partir de l'instant présent
   *\param TIMETICKS période d'activation
   */
  if(rt_task_set_periodic(NULL,TM_NOW,TIMERTICKS) !=0)
    {
      printf("Erreur rt_task_set_periodic!\n");
      return;
    }
    //boucle : Traitement effectué par la tâche à chacune de ses activations
    while(!FIN)
    {
      printf("Bonjour\n");
    rt_task_wait_period(NULL);
    }
}

int main(int argc, char*argv[]){
  RT_TASK TachePer;
  mlockall(MCL_CURRENT|MCL_FUTURE);
  /**
   *\param 2000 taille de la pile
   *\param 99 priorité 
   *\param T_JOINABLE
   *\param 
   */
  if(rt_task_spawn(&TachePer,"TachePer",2000,99,T_JOINABLE,&Code_TachePer,NULL)!=0)
    {
      printf("Erreur rt_task_spawn()!\n");
      return 1;
    }
  sleep(atoi(argv[1]));
  //la transformation d'une chaîne de caractère en entier
  FIN=1;
  rt_task_join(&TachePer);
  return 0;
}
