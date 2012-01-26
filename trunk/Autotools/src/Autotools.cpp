//============================================================================
// Name        : Autotools.cpp
// Author      : 
// Version     :
// Copyright   : Your copyright notice
// Description : Calcul de polynomes
//============================================================================
#include "Poly.h"
#include <iostream>
using namespace std;

int main() {
	int degree;
	double x;
	vector<double> coeffs;
	Poly mon_polynome;

	cout << "Quelle est le degré de votre Polynome ?" << endl;
	cin >> degree;
	for (int i = 0; i <= degree; i++) {
		double temp;
		cout << "Quelle est la valeur du coefficient de puissance " << degree - i  << " ?"
				<< endl;
		cin >> temp;
		coeffs.push_back(temp);
	}
	mon_polynome = Poly(coeffs);
	cout << "Quelle est la valeur de x ?" << endl;
	cin >> x;
	cout << "Le résultat du polynome est " << mon_polynome.calcul(x) << endl;
	return 0;
}
