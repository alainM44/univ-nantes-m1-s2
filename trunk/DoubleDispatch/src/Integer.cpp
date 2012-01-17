#include "Integer.h"
#include "Real.h"
#include <iostream>

using namespace std;

Integer::Integer(int n) 
{ 
	value = n;
}
Integer::Integer() 
{
}

Integer::~Integer() {
}
int Integer::getValue() const
{
	return value;
}
void  Integer::printValue() const
{
	cout << value <<endl;
}
Number* Integer::add(const Number* n) const
{
	return n->add(this);
}

Number* Integer::add(const Integer* n) const
{
	return new Integer(this->value +  n->getValue());
}

Number* Integer::add(const Real* n) const
{
	return n->add(this);
}
