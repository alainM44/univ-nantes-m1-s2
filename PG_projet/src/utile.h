/*! \class Utile
 *  \brief Fournies de outils généraux (non terminée).
 *  \author MARGUERITE RINCE
 *  \version 1.0
 *  \date   Mars 2012
 */

#ifndef UTILE_H_
#define UTILE_H_
template<class T>
class utile
{
	public:

		/*!
		 * \brief Swap
		 *
		 * Echange de valeur entre de vartiable de type T
		 *
		 * \param a : première valeur à échanger
		 * \param b : deuxième valeur à échanger
		 *
		 */
		void static swap(T& a, T& b);

};

#endif /* UTILE_H_ */
template<class T> inline
void utile<T>::swap(T& a, T& b) {
	T valTmp;
	valTmp = T(a);
	a = T(b);
	b = valTmp;

}
