#include "Parser.h"

using namespace std;

vector<string> Parser::Split_meta(const string &ligne) const {
	int pos = 0;
	vector<string> result;
	pos = ligne.find(':');
	result.push_back(ligne.substr(1, pos - 1));
	result.push_back(ligne.substr(pos + 1, ligne.size() - pos + 1));
	return result;
}

vector<string> Parser::Split_do(const string & ligne) const {
	int debut_donnee = 0;
	int fin_donnee;
	vector<string> result;
	// Parcours de la ligne en stockant les éléments et en "sautant" les espaces
	while (ligne.find(' ')) {
		fin_donnee = ligne.find(' ');
		result.push_back(ligne.substr(debut_donnee, fin_donnee - debut_donnee));
		debut_donnee = fin_donnee;
		while ((ligne.at(debut_donnee)) == ' ') {
			debut_donnee++;
		}
	}
	return result;
}

void Parser::parse(vector<vector<string> >& meta,
		vector<vector<string> >& donnees) const {
	//conversion de la string en char* pour permettre la création du ifstream.
	ifstream fichier(m_nom_fichier.c_str());
	string ligne;
	if (fichier) {
		while (getline(fichier, ligne)) {
			if (ligne.at(0) == '>') {
				meta.push_back(Split_meta(ligne));
			}
			else
			{
				donnees.push_back(Split_do(ligne));
			}
		}
	}
}

Parser::Parser(const string nom_fichier) :
	m_nom_fichier(nom_fichier) {

}

Parser::~Parser() {
	//dtor
}

