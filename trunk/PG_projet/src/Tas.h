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
#include <ostream>
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
			if (fils == 0)
				return -1;
			else
				return ((fils + 1) / 2) - 1;
		}//a vérifier

	public:
		Tas() {
			tas = std::vector<T>();
		}

		Tas(Tas& source) {
			this.tas = source.tas;
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
			T feuille; //!!
			T racine;
			if (tas.size() == 0)
			{
				throw;
			} else if (tas.size() == 1)
			{
				racine = tas.at(0);
				tas.pop_back();
			} else
			{
				racine = T(tas.at(0));
				feuille = tas.at(tas.size() - 1);
				//				std::cout << tas.size();
				//				std::cout << feuille;
				//				std::cout << racine;

				tas.at(0) = T(feuille);

				tas.pop_back();
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
			int max;

			if (filsd < tas.size())
			{
				max = element;
				if (tas.at(filsg) > tas.at(max))
					max = filsg;
				if (tas.at(filsd) > tas.at(max))
					max = filsd;
			} else if (filsg < tas.size())
			{
				max = element;
				if (tas.at(filsg) > tas.at(max))
					max = filsg;
			} else
			{
				max = element;
			}
			if (filsg < tas.size() && tas.at(filsg) > tas.at(element))
			{
				max = filsg;
			} else if (filsd < tas.size() && tas.at(filsd) > tas.at(element))
			{
				max = filsd;
			}
			if (max != element)
			{
				swap(tas.at(element), tas.at(max));
				Tas<T>::tasser(max);
			}
		}

		void swap(T& a, T& b) {
			T valTmp;
			valTmp = T(a);
			a = T(b);
			b = valTmp;

		}

		bool index_in_range(int i) const {
			return i < (tas.size());
		}
		void ajouter(const T& element) {
			int emplacementElement;
			int ancetre;
			tas.push_back(element);
			emplacementElement = tas.size() - 1;
			ancetre = Pere(emplacementElement);
			while (ancetre != -1 && tas.at(emplacementElement)
					> tas.at(ancetre))
			{
				swap(tas.at(emplacementElement), tas.at(ancetre));
				emplacementElement = ancetre;
				ancetre = Pere(emplacementElement);
			}

		}
		virtual ~Tas() {
		}

		void print() {
			std::cout << "[";
			for (int i = 0; i < tas.size(); i++)
				std::cout << tas.at(i);
			std::cout << "]" << std::endl;
		}
		;

		class Iterator;

		Iterator begin() {
			return Iterator(this);
		}
		Iterator end() {
			return Iterator(this, size());
		}

		//iterator end();

};

template<class T>
class Iterator
{
		friend class Tas<T> ;
		typedef Tas<T> tas_type;
		//	typedef std::list<T&> pile_type;
		typedef std::map<int, int> map_type;

	public:

		Iterator<T> (tas_type *t) {
			t_ = t;
			pos_ = 0;
			fg_estVisite = false;
			fd_estVisite = false;
		}
		//Iterator sale car mauvais utilisation du size pour permettre le end
		Iterator<T> (tas_type *t, int size) {
			t_ = t;
			pos_ = size;
			fg_estVisite = false;
			fd_estVisite = false;
		}
		Tas<T>& operator*() {
			if (pos_ < t_.size())
				return *t_->tas[pos_]; //a vérifier
			else
				return NULL;
		}

		Iterator<T>* operator++() {
			int fg = t_->filsG(pos_);
			int fd = t_->filsD(pos_);
			int pere = t_->pere(pos_);
			int filsT = pos_;
			//il y a un fils gauche
			if (!fg_estVisite && t_->index_in_range(fg))
			{
				pos_ = fg;
				return this;
				//Fils gauche visité et il y a un fils droit
			} else if (!fd_estVisite && t_->index_in_range(fd))
			{
				pos_ = fd;
				return this;
			} else
			{
				while (filsT != -1 && ((filsT == t_->filsD(pere)) || (filsT
						== t_->filsG(pere) && !t_->index_in_range(t_->filsD(
						pere)))))
				{
					filsT = pere;
					pere = t_->pere(pere);
				}
				//On est à la racine c'est la fin du parcours
				if (filsT == -1)
				{
					//TODO ajoute du cas filsT = -1 renvoi end
					return NULL;
					//On doit parcourir le fils droit de l'ancêtre
				} else
				{
					pos_ = t_->filsD(pere);

				}

				return this;
			}
		}

	private:
		tas_type * t_;
		int pos_;
		bool fg_estVisite;
		bool fd_estVisite;
		//pile_type p_;
		//map_type couleurs_noeuds;
		//		Iterator<T> (tas_type * t) {
		//			t_ = t;
		//			p_ = pile_type();Tas<T>& operator*() {
		//			pos_ = 0;
		//			//tant que qu'en fin de liste on a pas la racine
		//			//on enfile en queule a gauche tant que quil y a un filst gauche
		//			visiter(t->tas[0]);
		//
		//		}

		//		void visiter(int noeud) {
		//			int fg = t_ > t_->filsG();
		//			int fd = t_ > t_->filsD();
		//
		//			p_->push_back(noeud);
		//			couleurs_noeuds[noeud] = 1; //marquage du sommet explore
		//
		//			if (t_->index_in_range(fg) && (couleurs_noeuds[fg] == 0))
		//				visiter(fg);
		//			if (t_->index_in_range(fd) && (couleurs_noeuds[fd] == 0))
		//				visiter(fd);
		//
		//		}

		//		Tas<T>& operator*() {
		//			assert(pos_ < t_.size());
		//			return *t_->tas[p_.pop_front()]; //a vérifier
		//		}
};

#endif /* TAS_H_ */
