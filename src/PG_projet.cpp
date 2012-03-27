//============================================================================
// Name        : PG_projet.cpp
// Author      : 
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>

using namespace std;
#include "Tas.h"

int main() {
	typedef Tas<int,std::greater<int>,std::allocator<int> > tas_int;
	typedef Tas<int,std::greater<int>,std::allocator<int> >::Iterator it_int;
	typedef Tas<int,std::greater<int>,std::allocator<int> >::IteratorBfs it_bfs;
	tas_int t1;

	it_int it;
	it_int it2;
	it_bfs itb;
	int y;

	int x = 1;

	t1.ajouter(x);
	y = 2;
	t1.ajouter(y);
	int z = 4;
	t1.ajouter(z);
	int zz = 8;
	t1.ajouter(zz);
	int zzz = 5;
	t1.ajouter(zzz);
//	t1.print();
	//t1.tasser();
	it = t1.begin();
	it2 = t1.begin();
cout << (it!=it2);
++it;
cout << (it!=it2);


	//
//	t1.print();
	cout << (*it);
	++it;
	cout << (*it);
	++it;
	cout << (*it);
	++it;
	cout << (*it);
	++it;
	cout << (*it);
	//++it;

cout << t1.extraire();
	cout << t1.extraire();
t1.print();
	return 0;
}
