#ifndef PARSER_H_
#define PARSER_H_
#include <fstream>
#include <vector>
#include <string>

/*! \class Parser
 * \brief classe permettant le parsing du fichier d'entrée.
 *
 *  La classe rentre les données dans des tableaux de types vector
 */
class Parser {

public:

	/*!
	 * \brief décompose une ligne de Métadonnées pour retourner un tableau de 2 cases
	 * \brief contenant le nom de la métadonnée dans la première case et
	 * \brief le contenu dans le second
	 * @param ligne ligne de métadonnée a parser
	 * @return tableau remplit avec les meta données
	 * 
	 */
	std::vector<std::string> Split_meta(const std::string & ligne) const;

	/*!
	 *\brief decompose une ligne de données du tableau et retourne dans un vecteur les
	 *\brief valeurs extraites
	 * @param ligne ligne a parser
	 * @return tableau remplit avec les meta données
	 */
	std::vector<std::string> Split_do(const std::string &ligne) const;

	/*!
	 *\brief L'utilisateur fourni deux tableaux vides (de préférence) qui seront remplis respectivement
	 *\brief par les métadonnées et les données du fichier.
	 *@param meta Contient les métadonnées du fichier en sortie de la méthode.
	 *@param donnees Contient les données du fichier en sortie de la méthode.
	 */
	void parse(std::vector<std::vector<std::string> >& meta, std::vector<
			std::vector<std::string> >& donnees) const;

	/*!
	 * destructeur
	 */
	~Parser();

	/*!
	 * Constructeur
	 */
	Parser(const std::string nom_fichier);

private:
	std::string m_nom_fichier;

};
#endif

