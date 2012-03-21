/*
 * utile.h
 *
 *  Created on: 21 mars 2012
 *      Author: E11A932Q
 */

#ifndef UTILE_H_
#define UTILE_H_
template<class T>
class utile
{
	public:
		utile();
		virtual ~utile();

		void swap(T& a, T& b);

};

#endif /* UTILE_H_ */
template<class T> inline
void utile<T>::swap(T& a, T& b) {
	T valTmp;
	valTmp = T(a);
	a = T(b);
	b = valTmp;

}
