/*
 * Tas.h
 *
 *  Created on: 24 janv. 2012
 *      Author: E11A932Q
 */

#ifndef TAS_H_
#define TAS_H_
#include <vector>

template<class T> class Tas {
private:
	std::vector<T> tas;
	int filsG(int pere) {
		return (2 * (pere + 1)) - 1;
	}
	int filsD(int pere) {
		return (2 * (pere + 1));
	}
	int Pere(int fils) {
		if (fils = 0)
			return -1;
		else
			return ((fils + 1) / 2) - 1;
	}//a vérifier

public:
	Tas();
	T& extraire() {
		T& feuille, racine;
		if (tas.size() == 0) {
			throw;
		} else if (tas.size() == 1) {
			racine = tas.at(0);
			tas.pop_back();
		} else {
			feuille = tas.at(tas.size() - 1);
			tas.pop_back();
			racine = tas.at(0);
			tas.at(0) = feuille;
			tasser();
		}
		return racine;

	}
	void tasser() {
		//int filsg =filsG()
		Tas::tasser(0);

	}
	void tasser(int element) {
		int filsg = filsG();
		int filsd = filsD();
		int max, valTmp;
		if (filsg < tas.size() && tas.at(filsg) > tas.at(element)) {
			max = filsg;
		} else if (filsd < tas.size() && tas.at(filsd) > tas.at(element)) {
			max = filsd;
		}
		if (element < tas.size() && max != tas.at(element)) {
			valTmp = tas.at(element);
			tas.at(element) = tas.at(max);
			tas.at(max) = valTmp;
			Tas::tasser(max);

		}

	}
	void ajouter(const T& element) {
		int emplacementElement;
		int ancetre, tmpValue;
		bool changement = true;
		tas.push_back(element);
		emplacementElement = tas.size() - 1;
		ancetre = Pere(emplacementElement);
		while (ancêtre != -1 && tas.at(emplacementElement) > tas.at(ancetre)) {
			tas.at(emplacementElement) = tas.at(ancetre);
			tas.at(ancetre) = element;
		}

	}
	virtual ~Tas();

};

#endif /* TAS_H_ */
