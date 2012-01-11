//============================================================================
// Name        : TP1.cpp
// Author      : 
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include "Integer.h"
using namespace std;

int main() {
	Integer* n = new Integer(3);
	
	Integer m(4);
	
	delete n;
	return 0;
}
