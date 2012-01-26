/*
 * Poly.cpp
 *
 *  Created on: 24 janv. 2012
 *      Author: E11A932Q
 */

#include "Poly.h"
using namespace std;

Poly::Poly() {
coeff =  vector<double>();
}

Poly::Poly(const vector<double>& coeffs)
{
	coeff= coeffs;
}

double Poly::calcul(const double x)
{
	//Representation en Big Endian : poids fort en position 0
	double somme=0;
	for(size_t i=0; i<coeff.size(); i++)
	{
		somme += coeff.at(i)*pow(x, coeff.size()-i);
	}
	return somme;
}

Poly::~Poly() {
	// TODO Auto-generated destructor stub
}
