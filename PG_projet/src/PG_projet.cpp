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
typedef Tas<int> tas_int;
typedef Tas<int>::Iterator it_int;
tas_int t1;
//it_int it;

int x=1;

t1.ajouter(x);
// it = t1.begin();
//
//cout << (*ti) ;

cout << t1.extraire();

	return 0;
}
