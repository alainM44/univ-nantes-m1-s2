/*
 * Read_Param.h
 *
 *  Created on: 11 janv. 2012
 *      Author: alain
 */

#ifndef READ_PARAM_H_
#define READ_PARAM_H_
#include <vector>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <getopt.h>
#include <string>
#include <iostream>
/*!
 *\brief Classe permettant de récupérer les différentes fonctions fournies en ligne de commande
 *
 */
class Read_Param {


private:
	std::string legend;
	std::string outnamefile;


public:
	/*!
	 *\brief Constructeur
	 * Par si aucun nom de légendes ou de fichier de sortie n'est précisé en ligne de commande, les valeurs par défault suivantes sont appliquée
	 */
	Read_Param();
	/*!
	 * @param argc celui de la fonction main
	 * @param argv celui de la fonction main
	 *
	 *
	 *Parcours des options et mise à jours si besoin des attributs legend et outnamefile
	 */
	void set_Param(int argc, char* argv[]);
	virtual ~Read_Param();
	/*!
	 * \brief Affichage de l'aide. En cas de demande ou en cas de mauvaise utilisation.
	 */
	void print_usage();
};

#endif /* READ_PARAM_H_ */
