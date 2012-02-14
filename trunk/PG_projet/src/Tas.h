/*
 * Tas.h
 *
 *  Created on: 24 janv. 2012
 *      Author: E11A932Q
 */

#ifndef TAS_H_
#define TAS_H_
#include <vector>
#include <stack>
template<class T, class Compare = less<T> , class Allocator = allocator<T> > class Tas {
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
	Tas(Tas& source) {
		this.tas = source.tas;
	}
	int size()
	{
		return tas.size();
	}
	T& getMax() {
		return &tas.at(0);
	}
	T& get(int pos) {
		return &tas.at(pos);
	}
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

	class iterator;

	iterator begin() {
		return iterator(this, 0, new std::stack<int>());
	}
	;
	iterator end();

};

template<typename T>
class Tas<T>::iterator {
	friend class Tas;
	typedef Tas<T> tas_type;
	typedef std::stack<int> pile_type;

public:
	iterator(tas_type * t, int pos, pile_type * p) :
		t_(t), pos_(pos) p_(p) {
	}

	iterator& operator++() {
if (Tas<T>::filsG(pos)>= t_->size())
{
	if (Tas<T>::filsD(pos)>= t_->size())
	{

	}
}
	}

private:
	tas_type * t_;
	int pos_;
	pile_type * p_;

};

template<typename T>
Tas<T>::iterator Tas<T>::begin() {
	return iterator(this);
}

template<typename T>
Tas<T>::iterator Tas<T>::end() {
	return iterator(0);
}

#endif /* TAS_H_ */
