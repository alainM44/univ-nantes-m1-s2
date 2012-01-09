#ifndef PARSER_H_
#define PARSER_H_
#include <vector>
#include <string>

/*! \class Parser
 * \brief classe permettant le parsing du fichier d'entrée.
 *
 *  La classe rentre les données dans des tableaux de types vector
 */
class Parser
{

public :
	
	/**
	 * 
	 * @param ligne a parser
	 * @return tableau remplit avec les meta données
	 * 
	 */
	std::vector<std::string> Split_meta(const std::string & ligne, char separator) const;
	/**
	 * @param ligne a parser
	 * @return tableau remplit avec les meta données
	 */
	std::vector<std::string> Split_do(const std::string &ligne) const;
	/**
	 * destructeur
	 */
	~Parser();
	/**
	 * Constructeur
	 */
	Parser(std::string nom_fichier);
	
private: 
	std::string m_nom_fichier;

};
#endif

