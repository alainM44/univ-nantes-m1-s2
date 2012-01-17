#include "Real.h"
#include "Number.h"
#include "Integer.h"
#include <iostream>

using namespace std;

Real::Real(float n)
{
	value = n;
}
Real::Real()
{
}

Real::~Real()
{
}
float Real::getValue() const
{
	return value;
}
void  Real::printValue() const
{
	cout << value <<endl;
}
Number* Real::add(const Number* n) const
{
	return n->add(this);
}

Number* Real::add(const Integer* n) const
{
	return new Real(this->value +  n->getValue());
}

Number* Real::add(const Real* n) const
{
	return new Real(this->value +  n->getValue());
}
