#include "Parser.h"

using namespace std;



vector<string> Parser::Split_meta(const string &ligne, char separator) const
{
	int pos=0;
	vector <string> result;
	pos=ligne.find(separator);
	result.push_back(ligne.substr(1,pos-1)) ;
	result.push_back(ligne.substr(pos+1,ligne.size()-pos+1));
	return result;
} 

vector<string> Parser::Split_do(const string &ligne) const
{
	//
	int debut_donnee=0;
	int fin_donnee;
	vector <string> result; 
	// Parcours de la ligne en stockant les élémant et en "sautant" les espaces
	while(ligne.find(ligne,separator))
	{
		fin_donnee=ligne.find(separator);
		result.push_back(ligne.substr(debut_donnee,fin_donnee-debut_donne));
		debut_donnee=fin_donnee;
		while(ligne.at(debut_donnee)== " ")
		{
			debut_donnee++;
		}
	}
	return result;
} 



Parser::Parser(string nom_fichier) : m_nom_fichier(nom_fichier)
{

}

Parser::~Parser()
{
	//dtor
}






