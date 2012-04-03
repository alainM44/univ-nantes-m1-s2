/*! \class Tas
 *  \brief Arborescence générique sous forme de tas binaire
 *  \author MARGUERITE RINCE
 *  \version 1.0
 *  \date   Mars 2012
 */

#ifndef TAS_H_
#define TAS_H_

#include <vector>
#include <ostream>
#include "utile.h"
#include "Arbre_Vide.h"

/*! \class Tas
 * \brief classe representant le tas bianire
 *
 *
 *  La classe gere la lecture d'une liste de morceaux
 */

template<typename MatrixType1, typename MatrixType2>
void transpose(const MatrixType1& A, MatrixType2& At);

template<class T, class Compare = std::less<T>, class Alloc = std::allocator<T> >
class Tas
{
		friend class Iterator;

	private:
		std::vector<T, Alloc> tas; /*!<Structure conrète de l'arborescence*/

		/*!
		 * \fn int filsG(const int pere) {
		 * \brief Fils gauche
		 * \param[out] pere indice du père de l'enfant gauche recherché
		 * \return indice du fils gauche dans la structure concrète
		 */
		int filsG(const int pere) {
			return (2 * (pere + 1)) - 1;
		}
		/*!
		 * \fn int filsD(const int pere) {
		 * \brief Fils droit
		 * \param[out] pere indice du père de l'enfant droit recherché
		 * \return indice du fils droit dans la structure concrète
		 */
		int filsD(const int pere) {
			return (2 * (pere + 1));
		}

		/*!
		 * \fn int Pere(const int fils)
		 * \brief Père
		 * \param[out] fils indice du fils du père recherché
		 * \return indice du fils droit dans la structure concrète
		 */
		int Pere(const int fils) {
			if (fils == 0)
				return -1;
			else
				return ((fils + 1) / 2) - 1;
		}

	public:
		/*!
		 * \fn Tas()
		 * \brief Constructeur
		 * Constructeur par défaut de la classe Tas
		 */
		Tas();
		/*!
		 * \fn Tas()
		 * \brief Constructeur Recopie
		 * \param[out] source Tas à recopier
		 *
		 * Constructeur par recopie de la classe Tas
		 */

		Tas(Tas& source);

		/*!
		 * \fn int size()
		 * \brief Taile
		 * \return le nombre d'éléments du tas
		 */
		int size();
		/*!
		 * \fn T& get(int pos)
		 * \brief Accès au tas
		 * \param[out] indice dans la structure concrète de l'élément désiré.
		 * \return une référence de l'objet à la position précisée en paramètre
		 */
		T& get(int pos);

		/*!
		 * \fn T extraire()
		 * \brief Extraire
		 * \return racine du tas.
		 */
		T extraire();

		/*!
		 * \fn void tasser();
		 * \brief Tasser
		 * Tasse un tas à partir de la racine
		 */
		void tasser();
		/*!
		 * \fn void tasser(int element);
		 * \brief Tasser element
		 * \param[out] element indice de l'élément à partir duqel on veut tasser.
		 * Tasse un tas à partir de l'élément précisé
		 */
		void tasser(int element);

		/*!
		 * \fn 	bool indice_valide(int i) const;
		 * \brief Indice valide
		 * \param[out] i indice à tester
		 * \return vrai si l'indices est vrai faux sinon
		 * Test si l'indice est dans le champs la taille du tableau moins un
		 */
		bool indice_valide(int i) const;

		/*!
		 * \fn 	void ajouter(const T& element)
		 * \brief Ajouter
		 * \param[out] element  à ajouter
		 * Ajouter un élément dans le tas
		 */
		void ajouter(const T& element);
		/*!
		 * \fn 	virtual ~Tas()
		 * \brief Destructeur
		 * Déstructeur de la classe tas
		 */
		virtual ~Tas();

		//non documentation pratique pour le débug
		//		void print() {
		//			std::cout << "[";
		//			for (int i = 0; i < (int)tas.size(); i++)
		//				std::cout << tas.at(i);
		//			std::cout << "]" << std::endl;
		//		}

		/*! \class Iterator
		 *  \brief iterator DFS de la class Tas
		 *  \author MARGUERITE RINCE
		 *  \version 1.0
		 *  \date   Mars 2012
		 */

		class Iterator;

		/*!
		 * \fn 	Iterator begin()
		 * \brief Iterator début
		 * \return instance d'un Iterator
		 * Retourne un Iterator pointant sur le début du tas
		 */
		Iterator begin() {
			return Iterator(this);
		}
		/*!
		 * \fn Iterator end()
		 * \brief Iterator fin
		 * \return instance d'un Iterator
		 * Retourne un Iterator pointant sur la fin du tas
		 */
		Iterator end() {
			return Iterator(this, size());
		}

};
/*! \class Iterator
 * \brief classe d'itérateur de tas bianreisbianire
 */
template<class T, class Compare = std::less<T>, class Alloc = std::allocator<T> >
class Tas<T, Compare, Alloc>::Iterator
{

	private:
		typedef Tas<T, Compare, Alloc> tas_type;
		tas_type * t_;/*!<Pointeur sur le tas cible*/
		int pos_;/*!<Pointeur sur le tas cible*/
		bool fg_estVisite; /*!<Flag pour savoir si l'itérateur a déjà parcouru le fils gauche de la position courante */
		bool fd_estVisite;/*!<Flag pour savoir si l'itérateur a déjà parcouru le fils droit de la position courante */

	public:
		/*!
		 * \fn Iterator()
		 * \brief Constructeur
		 * Constructeur par défaut de la classe Tas::Iterator
		 */
		Iterator();
		/*!
		 * \fn Iterator(tas_type*t)
		 * \brief Constructeur à partir d'un pointeur du tas
		 * \param[out] Pointeur du tas à recopier
		 *
		 */
		Iterator(tas_type*t);
		/*!
		 * \fn Iterator(tas_type *t, int size)
		 * \brief Constructeur à partir d'un pointeur du tas et d'un emplacement
		 * \param[out] t Pointeur du tas à recopier
		 * \param[out] pos indice de l'élément à pointer dans le tas
		 */
		Iterator(tas_type *t, int pos);

		/*!
		 * \fn T operator*()
		 * Renvoit un l'element pointé
		 */
		T operator*() { // & or not & that is the question

			return (t_->tas[pos_]);

		}

		/*!
		 * \fn Iterator operator++()
		 * déplace d'une unité en "avant" selon DFS
		 */
		Iterator operator++();
		/*!
		 * \fn Iterator operator==()
		 * \param I Iterator à tester
		 *  Test l'égalité entre les deux  éléments pointés par deux itérateur
		 */
		bool operator==(const Iterator I);

		/*!
		 * \fn Iterator operator!=()
		 * \param I Iterator à tester
		 *  Test la différence  entre les deux  éléments pointés par deux itérateur
		 */
		bool operator!=(const Iterator I);

};
/**** DEFINITION DES CORPS DES FONCTIONS de TAS.h*****/
template<class T, class Compare, class Alloc>
inline Tas<T, Compare, Alloc>::Tas() {
	tas = std::vector<T>();
}

template<class T, class Compare, class Alloc>
inline Tas<T, Compare, Alloc>::Tas(Tas& source) {
	this.tas = source.tas;
}

template<class T, class Compare, class Alloc>
inline int Tas<T, Compare, Alloc>::size() {
	return tas.size();
}

template<class T, class Compare, class Alloc>
inline T& Tas<T, Compare, Alloc>::get(int pos) {
	return &tas.at(pos);
}
template<class T, class Compare, class Alloc>
inline T Tas<T, Compare, Alloc>::extraire() {
	T feuille;
	T racine;
	if (tas.size() == 0)
	{
		throw;// Arbre_Vide();
	} else if (tas.size() == 1)
	{
		racine = tas.at(0);
		tas.pop_back();
	} else
	{
		racine = T(tas.at(0));
		feuille = tas.at(tas.size() - 1);
		tas.at(0) = T(feuille);
		tas.pop_back();
		tasser();
	}
	return racine;

}
template<class T, class Compare, class Alloc>
inline void Tas<T, Compare, Alloc>::tasser() {
	Tas::tasser(0);
}

template<class T, class Compare, class Alloc>
inline void Tas<T, Compare, Alloc>::tasser(int element) {
	int filsg = Tas<T, Compare, Alloc>::filsG(element);
	int filsd = Tas<T, Compare, Alloc>::filsD(element);
	int max;
	Compare c;
	if (filsd < (int) tas.size())
	{
		max = element;
		if (c(tas.at(max), tas.at(filsg)))
			max = filsg;
		if (c(tas.at(max), tas.at(filsd)))
			max = filsd;
	} else if (c(filsg, (int) tas.size()))
	{
		max = element;
		if (c(tas.at(max), tas.at(filsg)))
			max = filsg;
	} else
	{
		max = element;
	}
	if (filsg < (int) tas.size() && c(tas.at(element), tas.at(filsg)))
	{
		max = filsg;
	} else if (filsd < (int) tas.size() && c(tas.at(element), tas.at(filsd)))
	{
		max = filsd;
	}
	if (max != element)
	{
		swap(tas.at(element), tas.at(max));
		cout << "tasser";
		Tas<T, Compare, Alloc>::tasser(max);
	}
}
template<class T, class Compare, class Alloc>
inline bool Tas<T, Compare, Alloc>::indice_valide(int i) const {
	return i < (int) (tas.size());
}
template<class T, class Compare, class Alloc>
inline void Tas<T, Compare, Alloc>::ajouter(const T& element) {
	int emplacementElement;
	int ancetre;
	Compare c;
	tas.push_back(element);
	emplacementElement = tas.size() - 1;
	ancetre = Pere(emplacementElement);
	while (ancetre != -1 && c(tas.at(ancetre), tas.at(emplacementElement)))
	{
		swap(tas.at(emplacementElement), tas.at(ancetre));
		emplacementElement = ancetre;
		ancetre = Pere(emplacementElement);
	}

}
template<class T, class Compare, class Alloc>
inline Tas<T, Compare, Alloc>::~Tas() {
}

/**** DEFINITION DES CORPS DES FONCTIONS de Tas::Iterator*****/
template<class T, class Compare, class Alloc>
inline Tas<T, Compare, Alloc>::Iterator::Iterator() {
}
template<class T, class Compare, class Alloc>
inline Tas<T, Compare, Alloc>::Iterator::Iterator(tas_type* t) {
}

template<class T, class Compare, class Alloc>
inline Tas<T, Compare, Alloc>::Iterator::Iterator(tas_type*t, int pos) {
	t_ = t;
	pos_ = pos;
	fg_estVisite = false;
	fd_estVisite = false;
}
template<class T, class Compare, class Alloc>
inline typename Tas<T, Compare, Alloc>::Iterator Tas<T, Compare, Alloc>::Iterator::operator ++() {
	int fg = t_->filsG(pos_);
	int fd = t_->filsD(pos_);
	int pere = t_->Pere(pos_);
	int filsT = pos_;

	if (!fg_estVisite && t_->indice_valide(fg)) /*!<il y a un fils gauche*/
	{
		pos_ = fg;
		return (*this);

	} else if (!fd_estVisite && t_->indice_valide(fd)) /*!<Fils gauche visité et il y a un fils droit*/
	{
		pos_ = fd;
		return (*this);
	} else
	{
		while (filsT != -1 && ((filsT == t_->filsD(pere)) || (filsT
				== t_->filsG(pere) && !t_->indice_valide(t_->filsD(pere)))))
		{
			filsT = pere;
			pere = t_->Pere(pere);
		}

		if (filsT == -1) /*!<On est à la racine c'est la fin du parcours*/
		{

			return NULL;

		} else /*!</On doit parcourir le fils droit de l'ancêtre*/
		{
			pos_ = t_->filsD(pere);
		}

		return (*this);
	}
}

template<class T, class Compare, class Alloc>
inline bool Tas<T, Compare, Alloc>::Iterator::operator ==(const Iterator I) {

	return pos_ == I.pos_ && I.t_ == t_;
}
template<class T, class Compare, class Alloc>
inline bool Tas<T, Compare, Alloc>::Iterator::operator !=(const Iterator I) {

	return pos_ == I.pos_ && I.t_ == t_;
}
#endif /* TAS_H_ */
