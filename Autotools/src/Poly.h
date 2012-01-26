/*
 * Poly.h
 *
 *  Created on: 24 janv. 2012
 *      Author: E11A932Q
 */

#ifndef POLY_H_
#define POLY_H_
#include <vector>
#include <math.h>

class Poly {
private:
	std::vector<double> coeff;
public:
	Poly();
	Poly(const std::vector<double>& coeffs);
	virtual ~Poly();

	double calcul(const double x);
};

#endif /* POLY_H_ */
