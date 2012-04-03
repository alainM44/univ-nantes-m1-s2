/*! \class Arbre_Vide
 *  \brief Exception levée lorque l'on tente extraire dans un arbre vide.
 *  \author MARGUERITE RINCE
 *  \version 1.0
 *  \date   Mars 2012
 */
#ifndef ARBRE_VIDE_H_
#define ARBRE_VIDE_H_
#include <iostream>
class Arbre_Vide
{
	public:
		Arbre_Vide(){
			std::cout << "Exception : Arbre vide" <<std::endl;
		}
		virtual ~Arbre_Vide();
};

#endif /* ARBRE_VIDE*/
