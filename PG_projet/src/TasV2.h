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
#include <list>

#include <map>
template<class T, class Compare = std::less<T>, class Alloc = std::allocator<T> >
class Tas

{
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
		Tas() {
		}
		;

		Tas(Tas& source) {
			this.tas = source.tas;
		}

		void add(T e) {
			tas.push_back(e);//!!!!
			tasser(e);//!!!
		}
		int size() {
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
			if (tas.size() == 0)
			{
				throw;
			} else if (tas.size() == 1)
			{
				racine = tas.at(0);
				tas.pop_back();
			} else
			{
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
			int filsg = Tas<T>::filsG(element);
			int filsd = Tas<T>::filsD(element);
			int max, valTmp;
			if (filsg < tas.size() && tas.at(filsg) > tas.at(element))
			{
				max = filsg;
			} else if (filsd < tas.size() && tas.at(filsd) > tas.at(element))
			{
				max = filsd;
			}
			if (element < tas.size() && max != tas.at(element))
			{
				valTmp = tas.at(element);
				tas.at(element) = tas.at(max);
				tas.at(max) = valTmp;
				Tas::tasser(max);
			}
		}

		bool index_in_range(int i) const {
			return i < (tas.size());
		}
		void ajouter(const T& element) {
			int emplacementElement;
			int ancetre, tmpValue;
			bool changement = true;
			tas.push_back(element);
			emplacementElement = tas.size() - 1;
			ancetre = Pere(emplacementElement);
			while (ancetre != -1 && tas.at(emplacementElement)
					> tas.at(ancetre))
			{
				tas.at(emplacementElement) = tas.at(ancetre);
				tas.at(ancetre) = element;
			}

		}
		virtual ~Tas() {
		}
		;

		class Iterator;

		Iterator begin() {
			return Iterator(this);
		}

		//iterator end();

};


template<class T>
class Iterator
{
		friend class Tas<T> ;
		typedef Tas<T> tas_type;
		typedef std::list<T&> pile_type;
		typedef std::map<int, int> map_type;

	public:
		Iterator<T> (tas_type * t) {
			t_ = t;
			p_ = pile_type();
			pos_ = 0;
			//tant que qu'en fin de liste on a pas la racine
			//on enfile en queule a gauche tant que quil y a un filst gauche
			visiter(t->tas[0]);

		}
		void visiter(int noeud) {
			int fg = t_ > t_->filsG();
			int fd = t_ > t_->filsD();

			p_->push_back(noeud);
			couleurs_noeuds[noeud] = 1; //marquage du sommet explore

			if (t_->index_in_range(fg) && (couleurs_noeuds[fg] == 0))
				visiter(fg);
			if (t_->index_in_range(fd) && (couleurs_noeuds[fd] == 0))
				visiter(fd);

		}

		Tas<T>& operator*() {
			assert(pos_ < t_.size());
			return *t_->tas[p_.pop_front()]; //a vérifier
		}

	private:
		tas_type * t_;
		int pos_;
		pile_type p_;
		map_type couleurs_noeuds;

};



#endif /* TAS_H_ */
