/**
 *TD8 exo 2
 * Modifier le code de l'exo1
 *1) Récupérer et stocker la valeur du temps courant au début de l'application (dans le main())
 *2)Pour chacune des tâches apériodique :
 ***Récupérer la valeur du temps courant au moment de leur activation
 ***Faire la différence entre ce temps et le temps de début de l'application (=temps écoulé depuis le début de l'applivation)
 */

#include<stdio.h> /*printf()*/
#include<stdlib.h> /*NULL*/
#include<native/task.h>/*rt_task...*/ 
#include<native/timer.h>/*rt_task...*/ 
#include<native/sem.h>/*rt_task...*/ 
#include<sys/mman.h>



/**
 *NOUVEAU ELEMENTS POUR LEXO2
 *Rajout de variables globales:
 *RT_MUTEX mutex;
 *RT_TIME Last_Elapsed_Time_Value; // Valeur du temps écoulé depuis le départ de l'appli
 *RT_TIME Applicaton_Start_Time_Value; // Valeur du temps courant au depuis le départ de l'appli
 *Modification du code de la tâche TacheAper1
 */
#include<native/mutex.h> // rt_mutex.**/
/*Semaphore d'exclusion mutuelle
 * TYPE RT_MUTEX
 */
int rt_mutex_create(RT_MUTEX* mutex, const char* name);/*CREATION ET INITIALISATION*/

/**
 *\param RT_TIME : TM_INFINITE 
 */
int rt_mutex_acquire(RT_MUTEX* mutex,RTIME timeout);/*Acquisition*/
int rt_mutex_release(RT_MUTEX* mutex);/*Liberation*/
/**
 *\param RT_TIME date : temps courant (en nanosec)
 */
int rt_timer_inquire(RT_TIMER_INFO* info);/*Récupération des données liées au timer */



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
int rt_sem_p(RT_SEM * sem, RTIME timeout);
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
struct RT_TASK_INFO
{
  int bprio ;  /*!< priorite de base. */
  int cprio; /*!< Tpriorite courante. */
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

  RT_TASK_INFO info_TacheAper1;
  RT_TIMER_INFO infosTimer;
  while(1)
    {
      rt_sem_p(&sem_synch1,TM_INFINITE);
      printf("Tache Aper1 activée!\n");
      rt_task_inquire(NULL,&info_TacheAper1);

      printf("Priorité de la tacche Aper1 = %d\n",infos_TacheAP1.bprio);
      rt_mutex_acquire(&mutex,TM_INFINITE);
      rt_timer_inquire(&infosTIMER);
      Last_Elapsed_Time_Value = infos_Timer.date-Application_Start_Time_Value; // en nano sec
      printf("Temps écoulé depusi le début de l'appli = %llu secondes\n",Last_Elapsed_Time_Value/1e9);
      rt_mutex_release(&mutex);
    }//FIN DU WHILE
}

// MEME CHODE pour Code_Tache_AP2

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
}////////////////////????????????????????????????
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




/**
 *Rajout dans le main
 */

int main (int argc, char*argv[]){
  RT_TASK TacheHorloge, TacheAP1,TacheAP2;

  //!!!!!!
  RT_TIMER_INFO infos_Timer;
  if(rt_mutex_create(&mutex,"mutex")!=0)
  {
    printf("mutex_create échoué !\n");
    return 1;
  }
  rt_time_inquire(&infos_Timer);
  Application_Start_Time_Value=infos_Timer.date;
  //!!!!!! a faire avant la création des tâches  sinon pb_d'init de la variable Applivation_Start_Time_value


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



