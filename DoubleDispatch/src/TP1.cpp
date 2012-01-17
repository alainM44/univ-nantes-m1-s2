//============================================================================
// Name        : TP1.cpp
// Author      : 
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include "Integer.h"
#include "Real.h"
using namespace std;

int main() {
	Number* n = new Integer(3);
	Number * num = new Real(4.5);
	num->add(n);

n->add(num)->printValue();
	Integer m(4);
	

	delete n;
	delete num;
	return 0;
}
