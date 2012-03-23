/**
 *TD8 exo 1
 */

#include<stdio.h> /*printf()*/
#include<stdlib.h> /*NULL*/
#include<native/task.h>/*rt_task...*/ 
#include<native/timer.h>/*rt_task...*/ 
#include<native/sem.h>/*rt_task...*/ 

//#include<sys/mmamm.h>  /*mlockall()*/
#include <timer/timer.h>// car gestion de tâches périodiques


/**
 *Squelette du programme 
 *-inclusion de la période du timer
 *-Définition de la période du timer
 *-Déclaation des semaphores de synchronisation RT_SEM monsem;
 *-Code de la Tâche "Horloge"
 *-Cdode d ela Tache R1
 *-->Afficher sa priorité 
 *-Code de la Tache R2
 *-->Afficher sa priorité 
 *Code du main 
 **Déclaratio ndes descripteurs de tâches
 **Création et initialisation des sem  ----> int rt_sem_create(RT_SEM *sem,const char*name,unsigned long icount,int mode);
 **Création et initialisation des tâches
 **Attente de la terminaison
 */


/**
 *\param Description du semaphore
 *\param nom du semaphore (arbitraire)
 *\param valeur d'initialisation
 *\param Ordre de la fille d'attente des tâches sur le sémaphores S_FIFO S_PRIO
 */
int rt_sem_create(RT_SEM *sem,const char*name,unsigned long icount,int mode);

/**
 *
 *\param RT_SEM descripteur
 *\param RT_TIME délais durant lequel la tache se bloque en attente du sem TM_INFINITE (blocage pendant un temps infini.
 */
int rt_sem_p(RT_SEM * sem, RT_TIME timeout);
int rt_sem_v(RT_SEM * sem);


/**
 *
 *\param descripteur de la tâche
 *\para RT_TASK_INFO Structure de données associée à la tache remplie par l'appel.
 *
 */
int rt_task_inquire(RT_TASK* task, RT_TASK_INFO* info);

/**
 * \struct RT_TASK_INFO
 * \brief .
 *
 * Str_t est un petit objet de gestion de chaînes de caractères. 
 * La chaîne se termine obligatoirement par un zéro de fin et l'objet 
 * connait la taille de chaîne contient !
 */
struct
{
  int brio ;  /*!< priorite de base. */
  int cbrio; /*!< Tpriorite courante. */
  unsigned status; /*!< Statut de la tache */
  RTIME relpoint;/*!< Date de prochain reveil. */
  char name [XNOBJECT_NAME_LEN];/*!< Nom de la tâche fournie lors de la créatioan */
  RTIME exectime;/*!< temps d'execution en mode primaire(Xen) (en nano sec)*/
  int modeswitches; /*<!Nb de chgmt de mode primaire => secondaire*/
  int ctswitches; /*<!nb de context*/
  int pagefaults;/*<!nb de changement de page */
};


#define TIMERTICKS 1000000000  // En nano secondes



RT_SEM sem_synch1,sem_synch2;





static void Code_TacheAP1(void*arg){
  if(rt_task_set_periodique(NULL,TM_NOW,TIMERTICKS) !=0)
    {
      printf("Erreur rt_task_set_periodic!\n");
      return;
    }
  if(rt_task_set_periodique(NULL,TM_NOW,TIMERTICKS) !=0)
    {
      printf("Erreur rt_task_set_periodic!\n");
      return;
    }

}


static void Code_TacheHorloge(void* arg)
{
  // vérouiller les tâches mémoire
  // mlockall(MCL_CURRENT|MCL_FUTURE);
  //Rendre la tâche périodique

  int cpt=1;  //compteur pour savoir à quel moment se synchroniser avec R2

  if(rt_task_set_periodic(NULL,TM_NOW,TIMERTICKS)!=0)
    {
      printf("Erreur de rt_task_set_periodic");
      return ;/*exit(1);*/
    }
  mlockall(MCL_CURRENT|MCL_FUTURE);
  while(1){
    /*synchronisation ver R1*/
    rt_sem_v(&sem_synchR1);
    if(cpt==5)
      {
	rt_sem_v(&sem_synchR2);
	cpt=0;
      }
  }
  cpt++;
  rt_task_wait_period(NULL);
  mlockall(MCL_CURRENT|MCL_FUTURE);
  while(1)
    {
      /*Synchronisation avec horloge */
      rt_sem_p(&sem_synchR1,TM_INFINITE);
      printf("Tache Aper1 activée !\n");
      /*Recupération des sur la tache*/
      rt_task_inquire(NULL,&infosTacheAper1.bprio);
      printf("Priorité de TacheAper1 = %d\n",infosTacheAper1.bprio);
    }


}//FIN DU CODE TACHEAPER1 faire la même chose pour Aper2




int main (int argc, char*argv[]){
  RT_TASK TacheHorloge, TacheAper1,TacheAper2;
  /*Création des sem*/
  if(rt_sem_create(&sem_synchR1,"semR1",0,S_FIFO)!=0) /*init a  en FIFO*/
    {
      printf("sem_create() sem_synchroR1 a échoué !\n");
      return 1;
    }
  if(rt_sem_create(&sem_synchR2,"semR2",0,S_FIFO)!=0) /*init a  en FIFO*/
    {
      printf("sem_create() sem_synchroR2 a échoué !\n");
      return 1;
    }
  /*Création des tâche*/
  if(rt_task_spawn(&TacheHorloge,"Horloge",20000,99,T_JOINABLE,&CodeTacheHorlge,NULL)!=0)  // on donne uen prio élevé à horloge
    {
      printf("rt_task_spawn a échoué  creation tache horloge!\n");
      return 1;
    }
  if(rt_task_spawn(&TacheAP1,"R1",20000,98,T_JOINABLE,&CodeTacheAP1,NULL)!=0)  
    {
      printf("rt_task_spawn creation de R1 a échoué !\n");
      return 1;
    }
  if(rt_task_spawn(&TacheAP2,"R2",20000,98,T_JOINABLE,&CodeTacheAP2,NULL)!=0)  
    {
      printf("rt_task_spawn  creation de R1 a échoué !\n");
      return 1;
    }
  rt_task_join(&TacheHorloge);
  rt_task_join(&TacheAP1);
  rt_task_join(&TacheAP2);
  return 0;
}



